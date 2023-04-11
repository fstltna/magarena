[
    new AtUpkeepTrigger() {
        @Override
        public MagicEvent executeTrigger(final MagicGame game, final MagicPermanent permanent, final MagicPlayer upkeepPlayer) {
            final MagicPermanent enchantedEnchantment = permanent.getEnchantedPermanent();
            return (enchantedEnchantment.isEnchantment() && enchantedEnchantment.isController(upkeepPlayer)) ?
                new MagicEvent(
                    permanent,
                    upkeepPlayer,
                    this,
                    "SN deals 1 damage to PN."
                ) :
                MagicEvent.NONE;
        }
        @Override
        public void executeEvent(final MagicGame game,final MagicEvent event) {
            game.doAction(new DealDamageAction(event.getSource(),event.getPlayer(),1));
        }
    }
]
