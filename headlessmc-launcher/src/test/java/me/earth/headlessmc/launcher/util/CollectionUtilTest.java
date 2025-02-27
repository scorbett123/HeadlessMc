package me.earth.headlessmc.launcher.util;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CollectionUtilTest {
    @Test
    public void testListOf() {
        val first = "first";
        val second = "second";
        val list = CollectionUtil.listOf(first, second);
        Assertions.assertEquals(first, list.get(0));
        Assertions.assertEquals(second, list.get(1));
    }

}
