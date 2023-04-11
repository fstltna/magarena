[
    new MagicSpellCardEvent() {

        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            final int x=payedCost.getX();
            return new MagicEvent(
                cardOnStack,
                x,
                this,
                "Creatures PN controls become RN/RN until end of turn."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final int X = event.getRefInt();
            final MagicStatic PT = new MagicStatic(MagicLayer.SetPT, MagicStatic.UntilEOT) {
                @Override
                public void modPowerToughness(final MagicPermanent S, final MagicPermanent P, final MagicPowerToughness pt) {
                    pt.set(X,X);
                }
            };
            CREATURE_YOU_CONTROL.filter(event) each {
                game.doAction(new BecomesCreatureAction(it, PT));
            }
        }
    }
]
