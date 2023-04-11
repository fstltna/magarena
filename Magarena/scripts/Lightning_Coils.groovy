[
    new AtYourUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game,final MagicPermanent permanent,final MagicPlayer upkeepPlayer) {
            final int counters = permanent.getCounters(MagicCounterType.Charge);
            return permanent.getCounters(MagicCounterType.Charge) >= 5 ?
                new MagicEvent(
                    permanent,
                    counters,
                    this,
                    "PN remove all charge counters from SN and creates RN 3/1 red Elemental creature tokens with haste. " +
                    "Exile them at the beginning of the next end step."
                ) :
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.doAction(new ChangeCountersAction(event.getPlayer(),event.getPermanent(),MagicCounterType.Charge,-event.getRefInt()));
            event.getRefInt().times {
                game.doAction(new PlayTokenAction(
                    event.getPlayer(),
                    CardDefinitions.getToken("3/1 red Elemental creature token with haste"),
                    MagicPlayMod.EXILE_AT_END_OF_TURN
                ));
            }
        }
    }
]
