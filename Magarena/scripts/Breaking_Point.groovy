[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                cardOnStack.getOpponent(),
                new MagicMayChoice("Have ${cardOnStack} deal 6 damage to you?"),
                this,
                "PN may\$ have SN deal 6 damage to him or her. If PN doesn't, destroy all creatures. " +
                "Creatures destroyed this way can't be regenerated."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            if (event.isYes()) {
                game.doAction(new DealDamageAction(event.getSource(), event.getPlayer(), 6));
            } else {
                final Collection<MagicPermanent> targets = CREATURE.filter(event);
                for (final MagicPermanent target : targets) {
                    game.doAction(ChangeStateAction.Set(target,MagicPermanentState.CannotBeRegenerated));
                }
                game.doAction(new DestroyAction(targets));
            }
        }
    }
]
