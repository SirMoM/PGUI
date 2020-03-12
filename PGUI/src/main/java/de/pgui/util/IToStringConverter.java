package de.pgui.util;

public interface IToStringConverter<G> {
    G toObject(String string);

    String toString(G object);
}
