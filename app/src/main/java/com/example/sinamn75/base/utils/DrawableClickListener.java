package com.example.sinamn75.base.utils;
public interface DrawableClickListener {
    void onClick(DrawablePosition target);
    enum DrawablePosition {TOP, BOTTOM, LEFT, RIGHT}
}