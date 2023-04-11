def DelayedTrigger = {
    final MagicSource staleSource, final MagicPlayer stalePlayer ->
    return new AtEndOfTurnTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer eotPlayer) {
            game.addDelayedAction(new RemoveTriggerAction(this));
            return new MagicEvent(
                game.createDelayedSource(staleSource, stalePlayer),
                this,
                "PN creates a 1/1 white Spirit creature token with flying."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new PlayTokenAction(
                event.getPlayer(),
                CardDefinitions.getToken("1/1 white Spirit creature token with flying")
            ))
        }
    }
}

[
    new MagicPermanentActivation(
        new MagicActivationHints(MagicTiming.Token),
        "Token"
    ) {

        @Override
        public Iterable<? extends MagicEvent> getCostEvent(final MagicPermanent source) {
            return [
                new MagicPayManaCostEvent(source, "{W}"),
                new MagicSacrificeEvent(source)
            ];
        }

        @Override
        public MagicEvent getPermanentEvent(final MagicPermanent source, final MagicPayedCost payedCost) {
            return new MagicEvent(
                source,
                this,
                "PN creates a 1/1 white Spirit creature token with flying "+
                "at the beginning of the next end step."
            );
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new AddTriggerAction(
                DelayedTrigger(event.getSource(), event.getPlayer())
            ));
        }
    }
]
