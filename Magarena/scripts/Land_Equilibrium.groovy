[
    new OtherEntersBattlefieldTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPermanent otherPermanent) {
            return otherPermanent.isController(permanent.getController().getOpponent()) &&
                   otherPermanent.hasType(MagicType.Land) &&
                   otherPermanent.getController().getNrOfPermanents(MagicType.Land) >=
                   permanent.getController().getNrOfPermanents(MagicType.Land) ?
                new MagicEvent(
                    permanent,
                    otherPermanent.getController(),
                    this,
                    "PN sacrifices a land."
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            game.addEvent(new MagicSacrificePermanentEvent(
                event.getSource(),
                event.getPlayer(),
                SACRIFICE_LAND
            ));
        }
    }
]
