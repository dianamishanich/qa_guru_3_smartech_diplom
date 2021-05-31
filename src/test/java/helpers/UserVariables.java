package helpers;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "file:../secret1.properties",
})

public interface UserVariables extends Config {

    @Key("user.name")
    String userName();

    @Key("phone.number")
    String phoneNumber();

    @Key("user.email")
    String userEmail();

}
