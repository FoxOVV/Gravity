package com.example.my_framework;

public class CollisionDetectFW {
    static double object1X;
    static double object1Y;

    static double object2X;
    static double object2Y;

    static double radiusObject1;
    static double radiusObject2;

    static double dX;
    static double dY;

    static double distanceObjects;

    public static boolean collisionDetect(ObjectFW object1, ObjectFW object2) {
        object1X = object1.getHitBox().centerX();
        object1Y = object1.getHitBox().centerY();

        object2X = object2.getHitBox().centerX();
        object2Y = object2.getHitBox().centerY();

        radiusObject1 = object1.getRadius();
        radiusObject2 = object2.getRadius();

        dX = object1X - object2X;
        dY = object1Y - object2Y;

        distanceObjects = Math.sqrt(dX*dX+dY*dY);

        if (distanceObjects<(radiusObject1+radiusObject2)) {
            return true;
        } else {
            return false;
        }
    }
}
