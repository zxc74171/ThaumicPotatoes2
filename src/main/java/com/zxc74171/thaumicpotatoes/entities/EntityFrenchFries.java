package com.zxc74171.thaumicpotatoes.entities;

import javax.annotation.Nullable;

import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.sounds.ModSoundEvents;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFrenchFries extends EntityMob
{
    protected static final DataParameter<Byte> VEX_FLAGS = EntityDataManager.<Byte>createKey(EntityFrenchFries.class, DataSerializers.BYTE);
    private EntityLiving owner;
    @Nullable
    private BlockPos boundOrigin;
    private boolean limitedLifespan;
    private int limitedLifeTicks;

    public EntityFrenchFries(World worldIn)
    {
        super(worldIn);
        this.isImmuneToFire = true;
        this.moveHelper = new EntityFrenchFries.AIMoveControl(this);
        this.setSize(1F, 1F);
        this.experienceValue = 3;
    }

    /**
     * Tries to move the entity towards the specified location.
     */
    public void move(MoverType type, double x, double y, double z)
    {
        super.move(type, x, y, z);
        this.doBlockCollisions();
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        this.noClip = true;
        super.onUpdate();
        this.noClip = false;
        this.setNoGravity(true);

        if (this.limitedLifespan && --this.limitedLifeTicks <= 0)
        {
            this.limitedLifeTicks = 20;
            this.attackEntityFrom(DamageSource.STARVE, 1.0F);
        }
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(4, new EntityFrenchFries.AIChargeAttack());
        this.tasks.addTask(8, new EntityFrenchFries.AIMoveRandom());
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityFrenchFries.class}));
        this.targetTasks.addTask(2, new EntityFrenchFries.AICopyOwnerTarget(this));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(14.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
        this.dataManager.register(VEX_FLAGS, Byte.valueOf((byte)0));
    }

    public static void registerFixesVex(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityFrenchFries.class);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("BoundX"))
        {
            this.boundOrigin = new BlockPos(compound.getInteger("BoundX"), compound.getInteger("BoundY"), compound.getInteger("BoundZ"));
        }

        if (compound.hasKey("LifeTicks"))
        {
            this.setLimitedLife(compound.getInteger("LifeTicks"));
        }
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        if (this.boundOrigin != null)
        {
            compound.setInteger("BoundX", this.boundOrigin.getX());
            compound.setInteger("BoundY", this.boundOrigin.getY());
            compound.setInteger("BoundZ", this.boundOrigin.getZ());
        }

        if (this.limitedLifespan)
        {
            compound.setInteger("LifeTicks", this.limitedLifeTicks);
        }
    }

    public EntityLiving getOwner()
    {
        return this.owner;
    }

    @Nullable
    public BlockPos getBoundOrigin()
    {
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos boundOriginIn)
    {
        this.boundOrigin = boundOriginIn;
    }

    private boolean getVexFlag(int p_190656_1_)
    {
        int i = ((Byte)this.dataManager.get(VEX_FLAGS)).byteValue();
        return (i & p_190656_1_) != 0;
    }

    private void setVexFlag(int p_190660_1_, boolean p_190660_2_)
    {
        int i = ((Byte)this.dataManager.get(VEX_FLAGS)).byteValue();

        if (p_190660_2_)
        {
            i = i | p_190660_1_;
        }
        else
        {
            i = i & ~p_190660_1_;
        }

        this.dataManager.set(VEX_FLAGS, Byte.valueOf((byte)(i & 255)));
    }

    public boolean isCharging()
    {
        return this.getVexFlag(1);
    }

    public void setIsCharging(boolean p_190648_1_)
    {
        this.setVexFlag(1, p_190648_1_);
    }

    public void setOwner(EntityLiving ownerIn)
    {
        this.owner = ownerIn;
    }

    public void setLimitedLife(int limitedLifeTicksIn)
    {
        this.limitedLifespan = true;
        this.limitedLifeTicks = limitedLifeTicksIn;
    }
    
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        EntityItem entityitem = this.dropItem(ModItems.FRENCHFRIES, 2);

        if (entityitem != null)
        {
            entityitem.setNoDespawn();
        }
    }
    
    protected SoundEvent getAmbientSound()
    {
        return ModSoundEvents.ENTITY_FRIES_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return ModSoundEvents.ENTITY_FRIES_HURT;
    }
    
    protected SoundEvent getDeathSound()
    {
        return ModSoundEvents.ENTITY_FRIES_DEATH;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender()
    {
        return 15728880;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness()
    {
        return 1.0F;
    }

    /**
     * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
     * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
     */
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
    {
        this.setEquipmentBasedOnDifficulty(difficulty);
        this.setEnchantmentBasedOnDifficulty(difficulty);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    /**
     * Gives armor or weapon for entity based on given DifficultyInstance
     */
    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.setDropChance(EntityEquipmentSlot.MAINHAND, 0.0F);
    }

    class AIChargeAttack extends EntityAIBase
    {
        public AIChargeAttack()
        {
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            if (EntityFrenchFries.this.getAttackTarget() != null && !EntityFrenchFries.this.getMoveHelper().isUpdating() && EntityFrenchFries.this.rand.nextInt(7) == 0)
            {
                return EntityFrenchFries.this.getDistanceSqToEntity(EntityFrenchFries.this.getAttackTarget()) > 4.0D;
            }
            else
            {
                return false;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return EntityFrenchFries.this.getMoveHelper().isUpdating() && EntityFrenchFries.this.isCharging() && EntityFrenchFries.this.getAttackTarget() != null && EntityFrenchFries.this.getAttackTarget().isEntityAlive();
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            EntityLivingBase entitylivingbase = EntityFrenchFries.this.getAttackTarget();
            Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
            EntityFrenchFries.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
            EntityFrenchFries.this.setIsCharging(true);
            EntityFrenchFries.this.playSound(ModSoundEvents.MCDONALD, 1.0F, 1.0F);
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            EntityFrenchFries.this.setIsCharging(false);
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            EntityLivingBase entitylivingbase = EntityFrenchFries.this.getAttackTarget();

            if (EntityFrenchFries.this.getEntityBoundingBox().intersects(entitylivingbase.getEntityBoundingBox()))
            {
                EntityFrenchFries.this.attackEntityAsMob(entitylivingbase);
                EntityFrenchFries.this.setIsCharging(false);
            }
            else
            {
                double d0 = EntityFrenchFries.this.getDistanceSqToEntity(entitylivingbase);

                if (d0 < 9.0D)
                {
                    Vec3d vec3d = entitylivingbase.getPositionEyes(1.0F);
                    EntityFrenchFries.this.moveHelper.setMoveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                }
            }
        }
    }

    class AICopyOwnerTarget extends EntityAITarget
    {
        public AICopyOwnerTarget(EntityCreature creature)
        {
            super(creature, false);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return EntityFrenchFries.this.owner != null && EntityFrenchFries.this.owner.getAttackTarget() != null && this.isSuitableTarget(EntityFrenchFries.this.owner.getAttackTarget(), false);
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void startExecuting()
        {
            EntityFrenchFries.this.setAttackTarget(EntityFrenchFries.this.owner.getAttackTarget());
            super.startExecuting();
        }
    }

    class AIMoveControl extends EntityMoveHelper
    {
        public AIMoveControl(EntityFrenchFries vex)
        {
            super(vex);
        }

        public void onUpdateMoveHelper()
        {
            if (this.action == EntityMoveHelper.Action.MOVE_TO)
            {
                double d0 = this.posX - EntityFrenchFries.this.posX;
                double d1 = this.posY - EntityFrenchFries.this.posY;
                double d2 = this.posZ - EntityFrenchFries.this.posZ;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                d3 = (double)MathHelper.sqrt(d3);

                if (d3 < EntityFrenchFries.this.getEntityBoundingBox().getAverageEdgeLength())
                {
                    this.action = EntityMoveHelper.Action.WAIT;
                    EntityFrenchFries.this.motionX *= 0.5D;
                    EntityFrenchFries.this.motionY *= 0.5D;
                    EntityFrenchFries.this.motionZ *= 0.5D;
                }
                else
                {
                    EntityFrenchFries.this.motionX += d0 / d3 * 0.05D * this.speed;
                    EntityFrenchFries.this.motionY += d1 / d3 * 0.05D * this.speed;
                    EntityFrenchFries.this.motionZ += d2 / d3 * 0.05D * this.speed;

                    if (EntityFrenchFries.this.getAttackTarget() == null)
                    {
                        EntityFrenchFries.this.rotationYaw = -((float)MathHelper.atan2(EntityFrenchFries.this.motionX, EntityFrenchFries.this.motionZ)) * (180F / (float)Math.PI);
                        EntityFrenchFries.this.renderYawOffset = EntityFrenchFries.this.rotationYaw;
                    }
                    else
                    {
                        double d4 = EntityFrenchFries.this.getAttackTarget().posX - EntityFrenchFries.this.posX;
                        double d5 = EntityFrenchFries.this.getAttackTarget().posZ - EntityFrenchFries.this.posZ;
                        EntityFrenchFries.this.rotationYaw = -((float)MathHelper.atan2(d4, d5)) * (180F / (float)Math.PI);
                        EntityFrenchFries.this.renderYawOffset = EntityFrenchFries.this.rotationYaw;
                    }
                }
            }
        }
    }

    class AIMoveRandom extends EntityAIBase
    {
        public AIMoveRandom()
        {
            this.setMutexBits(1);
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            return !EntityFrenchFries.this.getMoveHelper().isUpdating() && EntityFrenchFries.this.rand.nextInt(7) == 0;
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return false;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            BlockPos blockpos = EntityFrenchFries.this.getBoundOrigin();

            if (blockpos == null)
            {
                blockpos = new BlockPos(EntityFrenchFries.this);
            }

            for (int i = 0; i < 3; ++i)
            {
                BlockPos blockpos1 = blockpos.add(EntityFrenchFries.this.rand.nextInt(15) - 7, EntityFrenchFries.this.rand.nextInt(11) - 5, EntityFrenchFries.this.rand.nextInt(15) - 7);

                if (EntityFrenchFries.this.world.isAirBlock(blockpos1))
                {
                    EntityFrenchFries.this.moveHelper.setMoveTo((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 0.25D);

                    if (EntityFrenchFries.this.getAttackTarget() == null)
                    {
                        EntityFrenchFries.this.getLookHelper().setLookPosition((double)blockpos1.getX() + 0.5D, (double)blockpos1.getY() + 0.5D, (double)blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }

                    break;
                }
            }
        }
    }
}