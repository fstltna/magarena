[
    new EntersBattlefieldTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPayedCost payedCost) {
            if (payedCost.isKicked() == false) {
                game.doAction(new AddTriggerAction(permanent, AtEndOfTurnTrigger.Sacrifice));
            }
            return MagicEvent.NONE;
        }
    }
]
