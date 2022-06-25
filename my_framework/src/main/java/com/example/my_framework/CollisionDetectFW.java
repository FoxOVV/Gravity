package com.example.my_framework;

public class CollisionDetectFW {
    public static boolean collisionDetect(ObjectFW object1, ObjectFW object2) {
        double object1X = object1.getHitBox().centerX();
        double object1Y = object1.getHitBox().centerY();

        double object2X = object2.getHitBox().centerX();
        double object2Y = object2.getHitBox().centerY();

        double radiusObject1 = object1.getRadius();
        double radiusObject2 = object2.getRadius();

        double dX = object1X - object2X;
        double dY = object1Y - object2Y;

        double distanceObjects = Math.sqrt(dX*dX+dY*dY);

        return distanceObjects < (radiusObject1 + radiusObject2);
    }
}
