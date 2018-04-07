/*
   Copyright 2017-2018 Kacper Duras

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
package pl.kacperduras.protocoltab.manager;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import net.md_5.bungee.api.chat.BaseComponent;
import org.apache.commons.lang.Validate;

public class ProtocolSlot {

    private final WrappedGameProfile profile;

    private String text;

    public ProtocolSlot(WrappedGameProfile profile, String text) {
        Validate.isTrue(profile != null, "Profile cannot be null!");
        Validate.isTrue(text != null, "Text cannot be null!");

        this.profile = profile;
        this.text = text;
    }

    public WrappedGameProfile getProfile() {
        return profile;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        Validate.isTrue(text != null, "Text cannot be null!");

        this.text = text;
    }

    public void setText(BaseComponent... component) {
        Validate.isTrue(component != null, "Component cannot be null!");

        this.setText(BaseComponent.toLegacyText(component));
    }

}
