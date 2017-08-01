package com.zxc74171.thaumicpotatoes.entities;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.zxc74171.thaumicpotatoes.init.ModItems;
import com.zxc74171.thaumicpotatoes.sounds.ModSoundEvents;

import java.util.List;
import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntitySpellcasterIllager;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class EntityFryCook extends EntitySpellcasterIllager
{
    private EntitySheep wololoTarget;
    private int blockBreakCounter;
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
    public int deathTicks;
    
    public EntityFryCook(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.isImmuneToFire = true;
        ((PathNavigateGround)this.getNavigator()).setCanSwim(true);
        this.experienceValue = 1000;
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityFryCook.AICastingSpell());
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.6D, 1.0D));
        this.tasks.addTask(4, new EntityFryCook.AISummonSpell());
        this.tasks.addTask(5, new EntityFryCook.AIAttackSpell());
        this.tasks.addTask(6, new EntityFryCook.AIWololoSpell());
        this.tasks.addTask(8, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityFryCook.class}));
        this.targetTasks.addTask(2, (new EntityAINearestAttackableTarget(this, EntityPlayer.class, true)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, (new EntityAINearestAttackableTarget(this, EntityVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(50.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(5.0D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    public static void registerFixesEvoker(DataFixer fixer)
    {
        EntityLiving.registerFixesMob(fixer, EntityFryCook.class);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);
        if (this.hasCustomName())
        {
            this.bossInfo.setName(this.getDisplayName());
        }
    }
    
    public void setCustomNameTag(String name)
    {
        super.setCustomNameTag(name);
        this.bossInfo.setName(this.getDisplayName());
    }
    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);
    }

    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else if (source != DamageSource.DROWN && !(source.getTrueSource() instanceof EntityZiemniak))
        {

                Entity entity1 = source.getTrueSource();

                if (entity1 != null && !(entity1 instanceof EntityPlayer) && entity1 instanceof EntityLivingBase && ((EntityLivingBase)entity1).getCreatureAttribute() == this.getCreatureAttribute())
                {
                    return false;
                }
                else
                {
                    if (this.blockBreakCounter <= 0)
                    {
                        this.blockBreakCounter = 20;
                    }

                    return super.attackEntityFrom(source, amount);
                }
            }
        
            return false;
        }
    
    protected void updateAITasks()
    {
    	if (this.ticksExisted % 20 == 0)
        {
            this.heal(2.0F);
        }

        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
        
    	{
    		super.updateAITasks();
    		
                if (this.blockBreakCounter > 0)
                {
                    --this.blockBreakCounter;

                    if (this.blockBreakCounter == 0 && this.world.getGameRules().getBoolean("mobGriefing"))
                    {
                        int i1 = MathHelper.floor(this.posY);
                        int l1 = MathHelper.floor(this.posX);
                        int i2 = MathHelper.floor(this.posZ);
                        boolean flag = false;

                        for (int k2 = -1; k2 <= 1; ++k2)
                        {
                            for (int l2 = -1; l2 <= 1; ++l2)
                            {
                                for (int j = 0; j <= 3; ++j)
                                {
                                    int i3 = l1 + k2;
                                    int k = i1 + j;
                                    int l = i2 + l2;
                                    BlockPos blockpos = new BlockPos(i3, k, l);
                                    IBlockState iblockstate = this.world.getBlockState(blockpos);
                                    Block block = iblockstate.getBlock();

                                    if (!block.isAir(iblockstate, this.world, blockpos) && block.canEntityDestroy(iblockstate, world, blockpos, this) && net.minecraftforge.event.ForgeEventFactory.onEntityDestroyBlock(this, blockpos, iblockstate))
                                    {
                                        flag = this.world.destroyBlock(blockpos, true) || flag;
                                    }
                                }
                            }
                        }

                        if (flag)
                        {
                            this.world.playEvent((EntityPlayer)null, 1022, new BlockPos(this), 0);
                        }
                    }
                }
    	}}
    

    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier)
    {
        EntityItem entityitem = this.dropItem(ModItems.TRUTH, 1);

        if (entityitem != null)
        {
            entityitem.setNoDespawn();
        }
    }
    
    protected void onDeathUpdate()
    {
        ++this.deathTicks;

        if (this.deathTicks >= 180 && this.deathTicks <= 200)
        {
            float f = (this.rand.nextFloat() - 0.5F) * 8.0F;
            float f1 = (this.rand.nextFloat() - 0.5F) * 4.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 8.0F;
            this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX + (double)f, this.posY + 2.0D + (double)f1, this.posZ + (double)f2, 0.0D, 0.0D, 0.0D);
        }

        boolean flag = this.world.getGameRules().getBoolean("doMobLoot");
        int i = 500;


        if (!this.world.isRemote)
        {
            if (this.deathTicks > 150 && this.deathTicks % 5 == 0 && flag)
            {
            }

            if (this.deathTicks == 1)
            {
                this.world.playBroadcastSound(1028, new BlockPos(this), 0);
            }
        }

        this.move(MoverType.SELF, 0.0D, 0.10000000149011612D, 0.0D);
        this.rotationYaw += 20.0F;
        this.renderYawOffset = this.rotationYaw;

        if (this.deathTicks == 200 && !this.world.isRemote)
        {
            if (flag)
            {
            }

            this.setDead();
        }
    }
    
    public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }
    
    public void onUpdate()
    {
        super.onUpdate();
    }
    
    public boolean isNonBoss()
    {
        return false;
    }
    /**
     * Returns whether this Entity is on the same team as the given Entity.
     */
    public boolean isOnSameTeam(Entity entityIn)
    {
        if (entityIn == null)
        {
            return false;
        }
        else if (entityIn == this)
        {
            return true;
        }
        else if (super.isOnSameTeam(entityIn))
        {
            return true;
        }
        else if (entityIn instanceof EntityVex)
        {
            return this.isOnSameTeam(((EntityVex)entityIn).getOwner());
        }
        else if (entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getCreatureAttribute() == EnumCreatureAttribute.ILLAGER)
        {
            return this.getTeam() == null && entityIn.getTeam() == null;
        }
        else
        {
            return false;
        }
    }

    protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty)
    {
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ModItems.POTATO_LAUNCHER));
        this.setDropChance(EntityEquipmentSlot.MAINHAND, 0.0F);
    }
    
    protected SoundEvent getAmbientSound()
    {
        return ModSoundEvents.ENTITY_FRYCOOK_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource p_184601_1_)
    {
        return ModSoundEvents.ENTITY_FRYCOOK_HURT;
    }
    
    protected SoundEvent getDeathSound()
    {
        return ModSoundEvents.ENTITY_FRYCOOK_DEATH;
    }



    private void setWololoTarget(@Nullable EntitySheep p_190748_1_)
    {
        this.wololoTarget = p_190748_1_;
    }

    @Nullable
    private EntitySheep getWololoTarget()
    {
        return this.wololoTarget;
    }

    protected SoundEvent getSpellSound()
    {
        return ModSoundEvents.LANLANLU;
    }

    class AIAttackSpell extends EntitySpellcasterIllager.AIUseSpell
    {
        private AIAttackSpell()
        {
            super();
        }

        protected int getCastingTime()
        {
            return 40;
        }

        protected int getCastingInterval()
        {
            return 100;
        }

        protected void castSpell()
        {
            EntityLivingBase entitylivingbase = EntityFryCook.this.getAttackTarget();
            double d0 = Math.min(entitylivingbase.posY, EntityFryCook.this.posY);
            double d1 = Math.max(entitylivingbase.posY, EntityFryCook.this.posY) + 1.0D;
            float f = (float)MathHelper.atan2(entitylivingbase.posZ - EntityFryCook.this.posZ, entitylivingbase.posX - EntityFryCook.this.posX);

            if (EntityFryCook.this.getDistanceSqToEntity(entitylivingbase) < 9.0D)
            {
                for (int i = 0; i < 5; ++i)
                {
                    float f1 = f + (float)i * (float)Math.PI * 0.4F;
                    this.spawnFangs(EntityFryCook.this.posX + (double)MathHelper.cos(f1) * 1.5D, EntityFryCook.this.posZ + (double)MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0);
                }

                for (int k = 0; k < 8; ++k)
                {
                    float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + ((float)Math.PI * 2F / 5F);
                    this.spawnFangs(EntityFryCook.this.posX + (double)MathHelper.cos(f2) * 2.5D, EntityFryCook.this.posZ + (double)MathHelper.sin(f2) * 2.5D, d0, d1, f2, 3);
                }
            }
            else
            {
                for (int l = 0; l < 16; ++l)
                {
                    double d2 = 1.25D * (double)(l + 1);
                    int j = 1 * l;
                    this.spawnFangs(EntityFryCook.this.posX + (double)MathHelper.cos(f) * d2, EntityFryCook.this.posZ + (double)MathHelper.sin(f) * d2, d0, d1, f, j);
                }
            }
        }

        private void spawnFangs(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_)
        {
            BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
            boolean flag = false;
            double d0 = 0.0D;

            while (true)
            {
                if (!EntityFryCook.this.world.isBlockNormalCube(blockpos, true) && EntityFryCook.this.world.isBlockNormalCube(blockpos.down(), true))
                {
                    if (!EntityFryCook.this.world.isAirBlock(blockpos))
                    {
                        IBlockState iblockstate = EntityFryCook.this.world.getBlockState(blockpos);
                        AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(EntityFryCook.this.world, blockpos);

                        if (axisalignedbb != null)
                        {
                            d0 = axisalignedbb.maxY;
                        }
                    }

                    flag = true;
                    break;
                }

                blockpos = blockpos.down();

                if (blockpos.getY() < MathHelper.floor(p_190876_5_) - 1)
                {
                    break;
                }
            }

            if (flag)
            {
                EntityPotatoFangs entityevokerfangs = new EntityPotatoFangs(EntityFryCook.this.world, p_190876_1_, (double)blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, EntityFryCook.this);
                EntityFryCook.this.world.spawnEntity(entityevokerfangs);
            }
        }

        protected SoundEvent getSpellPrepareSound()
        {
            return ModSoundEvents.DONARUTO;
        }

        protected EntitySpellcasterIllager.SpellType getSpellType()
        {
            return EntitySpellcasterIllager.SpellType.FANGS;
        }
    }

    class AICastingSpell extends EntitySpellcasterIllager.AICastingApell
    {
        private AICastingSpell()
        {
            super();
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask()
        {
            if (EntityFryCook.this.getAttackTarget() != null)
            {
                EntityFryCook.this.getLookHelper().setLookPositionWithEntity(EntityFryCook.this.getAttackTarget(), (float)EntityFryCook.this.getHorizontalFaceSpeed(), (float)EntityFryCook.this.getVerticalFaceSpeed());
            }
            else if (EntityFryCook.this.getWololoTarget() != null)
            {
                EntityFryCook.this.getLookHelper().setLookPositionWithEntity(EntityFryCook.this.getWololoTarget(), (float)EntityFryCook.this.getHorizontalFaceSpeed(), (float)EntityFryCook.this.getVerticalFaceSpeed());
            }
        }
    }

    class AISummonSpell extends EntitySpellcasterIllager.AIUseSpell
    {
        private AISummonSpell()
        {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            if (!super.shouldExecute())
            {
                return false;
            }
            else
            {
                int i = EntityFryCook.this.world.getEntitiesWithinAABB(EntityVex.class, EntityFryCook.this.getEntityBoundingBox().grow(16.0D)).size();
                return EntityFryCook.this.rand.nextInt(8) + 1 > i;
            }
        }

        protected int getCastingTime()
        {
            return 100;
        }

        protected int getCastingInterval()
        {
            return 340;
        }

        protected void castSpell()
        {
            for (int i = 0; i < 3; ++i)
            {
                BlockPos blockpos = (new BlockPos(EntityFryCook.this)).add(-2 + EntityFryCook.this.rand.nextInt(5), 1, -2 + EntityFryCook.this.rand.nextInt(5));
                EntityFrenchFries entityvex = new EntityFrenchFries(EntityFryCook.this.world);
                entityvex.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
                entityvex.onInitialSpawn(EntityFryCook.this.world.getDifficultyForLocation(blockpos), (IEntityLivingData)null);
                entityvex.setOwner(EntityFryCook.this);
                entityvex.setBoundOrigin(blockpos);
                entityvex.setLimitedLife(20 * (30 + EntityFryCook.this.rand.nextInt(90)));
                EntityFryCook.this.world.spawnEntity(entityvex);
            }
        }

        protected SoundEvent getSpellPrepareSound()
        {
            return SoundEvents.EVOCATION_ILLAGER_PREPARE_SUMMON;
        }

        protected EntitySpellcasterIllager.SpellType getSpellType()
        {
            return EntitySpellcasterIllager.SpellType.SUMMON_VEX;
        }
    }

    public class AIWololoSpell extends EntitySpellcasterIllager.AIUseSpell
    {
        final Predicate<EntitySheep> wololoSelector = new Predicate<EntitySheep>()
        {
            public boolean apply(EntitySheep p_apply_1_)
            {
                return p_apply_1_.getFleeceColor() == EnumDyeColor.BLUE;
            }
        };

        public AIWololoSpell()
        {
            super();
        }

        /**
         * Returns whether the EntityAIBase should begin execution.
         */
        public boolean shouldExecute()
        {
            if (EntityFryCook.this.getAttackTarget() != null)
            {
                return false;
            }
            else if (EntityFryCook.this.isSpellcasting())
            {
                return false;
            }
            else if (EntityFryCook.this.ticksExisted < this.spellCooldown)
            {
                return false;
            }
            else if (!EntityFryCook.this.world.getGameRules().getBoolean("mobGriefing"))
            {
                return false;
            }
            else
            {
                List<EntitySheep> list = EntityFryCook.this.world.<EntitySheep>getEntitiesWithinAABB(EntitySheep.class, EntityFryCook.this.getEntityBoundingBox().grow(16.0D, 4.0D, 16.0D), this.wololoSelector);

                if (list.isEmpty())
                {
                    return false;
                }
                else
                {
                    EntityFryCook.this.setWololoTarget(list.get(EntityFryCook.this.rand.nextInt(list.size())));
                    return true;
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean shouldContinueExecuting()
        {
            return EntityFryCook.this.getWololoTarget() != null && this.spellWarmup > 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void resetTask()
        {
            super.resetTask();
            EntityFryCook.this.setWololoTarget((EntitySheep)null);
        }

        protected void castSpell()
        {
            EntitySheep entitysheep = EntityFryCook.this.getWololoTarget();

            if (entitysheep != null && entitysheep.isEntityAlive())
            {
                entitysheep.setFleeceColor(EnumDyeColor.RED);
            }
        }

        protected int getCastWarmupTime()
        {
            return 40;
        }

        protected int getCastingTime()
        {
            return 60;
        }

        protected int getCastingInterval()
        {
            return 140;
        }

        protected SoundEvent getSpellPrepareSound()
        {
            return SoundEvents.EVOCATION_ILLAGER_PREPARE_WOLOLO;
        }

        protected EntitySpellcasterIllager.SpellType getSpellType()
        {
            return EntitySpellcasterIllager.SpellType.WOLOLO;
        }
    }
}