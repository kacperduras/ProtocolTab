/*
   Copyright 2017 Kacper Duras

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package pl.kacperduras.protocoltab.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import pl.kacperduras.protocoltab.ProtocolTabAPI;

public class EffSetSlot extends Effect {

    static {
        Skript.registerEffect(EffSetSlot.class, "set %player%'s slot number %integer% to %string%");
    }

    private Expression<Player> playerExpression;
    private Expression<Integer> integerExpression;
    private Expression<String> stringExpression;

    @Override
    protected void execute(Event event) {
        Player player = this.playerExpression.getSingle(event);
        Integer integer = this.integerExpression.getSingle(event);
        String string = this.stringExpression.getSingle(event);

        if (player == null || string == null) {
            return;
        }

        ProtocolTabAPI.getTablist(player).setSlot(integer, string);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        this.playerExpression = (Expression<Player>) expressions[0];
        this.integerExpression = (Expression<Integer>) expressions[1];
        this.stringExpression = (Expression<String>) expressions[2];

        return true;
    }

    @Override
    public String toString(Event event, boolean b) {
        return this.getClass().getName();
    }

}
