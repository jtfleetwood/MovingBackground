package com.gamecodeschool.escape;

public interface MovementComponent {
    boolean move(long fps, MovementInfo m, MovementInfo playerM);
}
