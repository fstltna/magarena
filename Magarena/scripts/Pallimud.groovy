[
    new MagicCDA() {
        @Override
        public void modPowerToughness(final MagicGame game, final MagicPlayer player, final MagicPermanent permanent, final MagicPowerToughness pt) {
            final int amt = permanent.getChosenPlayer().getNrOfPermanents(TAPPED_LAND);
            pt.setPower(amt);
        }
    }
]
