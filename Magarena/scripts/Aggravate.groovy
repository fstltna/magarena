[
    new MagicSpellCardEvent() {
        @Override
        public MagicEvent getEvent(final MagicCardOnStack cardOnStack,final MagicPayedCost payedCost) {
            return new MagicEvent(
                cardOnStack,
                NEG_TARGET_PLAYER,
                this,
                "SN deals 1 damage to each creature target player\$ " +
                "controls. Each creature dealt damage this way attacks " +
                "this turn if able."
            );
        }
        @Override
        public void executeEvent(final MagicGame game, final MagicEvent event) {
            event.processTargetPlayer(game, {
                CREATURE_YOU_CONTROL.filter(it) each {
                    final MagicDamage damage=new MagicDamage(event.getSource(),it,1);
                    game.doAction(new DealDamageAction(damage));
                    if (damage.getDealtAmount() > 0) {
                        game.doAction(new GainAbilityAction(it, MagicAbility.AttacksEachTurnIfAble));
                    }
                }
            });
        }
    }
]
