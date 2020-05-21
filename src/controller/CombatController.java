package controller;

import model.Combat;
import view.CombatPanel;

public class CombatController {
    final CombatPanel combatPanel;
    Combat combat;

    public CombatController(CombatPanel combatPanel) {
        this.combatPanel = combatPanel;
        addActionListeners();
    }

    public void run(Combat combat) {
        this.combat = combat;
        updateView();
    }

    private void addActionListeners() {
        combatPanel.btnAttack.addActionListener(e -> attack());
        combatPanel.btnSpecialAttack.addActionListener(e -> specialAttack());
        combatPanel.btnRecover.addActionListener(e -> recover());

    }

    private void attack() {
        printCombatLog(combat.attack());
    }

    private void specialAttack() {
        printCombatLog(combat.specialAttack());
    }

    private void recover() {
        printCombatLog(combat.recover());
    }

    private void printCombatLog(int result) {
        combatPanel.txtCombatLog.append(LogBuilder.buildTurnLog(result,
                combat.getPlayer().getName(),
                combat.getEnemy().getName()));
        combatPanel.txtCombatLog.append("\n");

        if (!combat.isCombatEnded()) {
            result = combat.botTurn();
            combatPanel.txtCombatLog.append(LogBuilder.buildTurnLog(result,
                    combat.getEnemy().getName(),
                    combat.getPlayer().getName()));
            combatPanel.txtCombatLog.append("\n");
        }
        updateView();
    }

    private void updateView() {
        combatPanel.prgPlayerHealth.setValue(combat.getPlayer().getCurrentHp());
        combatPanel.prgPlayerStamina.setValue(combat.getPlayer().getCurrentStamina());

        combatPanel.prgEnemyHealth.setValue(combat.getEnemy().getCurrentHp());
        combatPanel.prgEnemyStamina.setValue(combat.getEnemy().getCurrentStamina());

        combatPanel.btnAttack.setEnabled(combat.getPlayer().isAttackAvailable()
                && !combat.isCombatEnded());
        combatPanel.btnSpecialAttack.setEnabled(!combat.isSpecialAttackUsed()
                && combat.getPlayer().isSpecialAttackAvailable()
                && !combat.isCombatEnded());
        combatPanel.btnRecover.setEnabled(combat.getPlayer().getCurrentStamina() < combat.getPlayer().getMaxStamina()
                && !combat.isCombatEnded());
        combatPanel.btnExitCombat.setEnabled(combat.isCombatEnded());
    }
}