package com.zxc74171.thaumicpotatoes.entities;

import net.minecraft.entity.ai.EntityAIAttackMelee;

public class EntityAIJagaimoAttack extends EntityAIAttackMelee {
    private int raiseArmTicks;
    private EntityJagaimo lastEldritch;

    public EntityAIJagaimoAttack(EntityJagaimo zombieIn, double speedIn, boolean longMemoryIn) {
        super(zombieIn, speedIn, longMemoryIn);
        this.lastEldritch = zombieIn;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    @Override
    public void startExecuting() {
        super.startExecuting();
        this.raiseArmTicks = 0;
    }

    /**
     * Resets the task
     */
    @Override
    public void resetTask() {
        super.resetTask();
        this.lastEldritch.setArmsRaised(false);
    }

    /**
     * Updates the task
     */
    @Override
    public void updateTask() {
        super.updateTask();
        ++this.raiseArmTicks;

        if (this.raiseArmTicks >= 5 && this.attackTick < 10) {
            this.lastEldritch.setArmsRaised(true);
        } else {
            this.lastEldritch.setArmsRaised(false);
        }
    }
}