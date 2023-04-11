[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                NEG_TARGET_CREATURE,
                MagicDestroyTargetPicker.DestroyNoRegen,
                this,
                "Destroy target creature\$. It can't be regenerated. "+
                "PN loses life equal to that creature's toughness."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPermanent(game, {
                game.doAction(ChangeStateAction.Set(it,MagicPermanentState.CannotBeRegenerated));
                game.doAction(new DestroyAction(it));
                game.doAction(new ChangeLifeAction(event.getPlayer(),-it.getToughness()));
            });
        }
    }
]
