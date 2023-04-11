[
    new DamageIsDealtTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicDamage damage) {
            return damage.getTarget() == permanent ?
                new MagicEvent(
                    permanent,
                    damage.getDealtAmount(),
                    this,
                    "PN sacrifices RN permanents."
                ):
                MagicEvent.NONE;
        }

        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            final MagicPermanent permanent = event.getPermanent();
            final MagicPlayer player = event.getPlayer();
            int amount = event.getRefInt();
            while (amount > 0 && player.getPermanents().size() > 0) {
                game.addEvent(new MagicSacrificePermanentEvent(
                    permanent,
                    player,
                    SACRIFICE_PERMANENT
                ));
                amount--;
            }
        }
    }
]
