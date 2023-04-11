[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                NEG_TARGET_INSTANT_SPELL,
                this,
                "Counter target instant spell\$ if it's blue. " +
                "PN draws a card at the beginning of the next turn's upkeep."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetCardOnStack(game, {
                if (it.hasColor(MagicColor.Blue)) {
                    game.doAction(new CounterItemOnStackAction(it));
                }
                game.doAction(new AddTriggerAction(
                    AtUpkeepTrigger.YouDraw(event.getSource(), event.getPlayer())
                ));
            });
        }
    }
]
