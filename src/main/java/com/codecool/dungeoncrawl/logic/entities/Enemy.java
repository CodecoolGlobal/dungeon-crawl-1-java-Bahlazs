package com.codecool.dungeoncrawl.logic.entities;

public abstract class Enemy extends Entity {
    protected final double attackCooldown;
    protected boolean canAttack;
    protected double cooldownChecker = 0;

    public Enemy(String url, int x, int y, int speed, int size, double attackCooldown) {
        super(url, x, y, speed, size);
        this.attackCooldown = attackCooldown;
        canAttack = true;
    }

    public boolean attack(boolean entityIsNear, Player player) {
        checkAttackCooldown();
        if (entityIsNear && canAttack) {
            System.out.println("die!!!!!!!!!");
            player.damagePlayer(10);
            canAttack = false;
            return true;
        }
        return false;
    }
    private void checkAttackCooldown() {
        if (!canAttack) {
            cooldownChecker += 0.01;
            if (cooldownChecker >= attackCooldown) {
                cooldownChecker = 0;
                canAttack = true;
            }
        }
    }

}
