package me.earth.headlessmc.lwjgl.lwjgltestclasses;

/**
 * {@link me.earth.headlessmc.lwjgl.LwjglInstrumentationTest}
 */
@SuppressWarnings("unused")
public interface LwjglInterface {
    void abstractMethod();

    default int someMethod() {
        return 0;
    }

    static LwjglInterface factoryMethod(String dontCall) {
        return null;
    }

}
