package Model.Entities;

import Interfaces.ICollidable;
import Interfaces.IMovable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGameObject implements IMovable, ICollidable {
    private final List<HitBox> hitBoxes;
    private boolean collided;
    // Velocity (horizontal, vertical)
    public Point2D velocity;
    // movement speed
    private double speed;

    public AbstractGameObject() {
        this.hitBoxes = new ArrayList<>();
        hitBoxes.add(new HitBox());
    }

    //------------------------------------------------------

    /**
     * Move self to a new position.
     *
     * @param deltaTime the length of a frame in the game loop
     * @author Irja Vuorela
     */
    @Override
    public abstract void move(double deltaTime);

    /**
     * Update the position of a movable object
     *
     * @param deltaTime the length of a frame in the game loop
     * @authors Irja Vuorela and Tobias Engblom
     */
    protected void updatePosition(double deltaTime) {
        this.velocity = velocity.multiply(deltaTime);
        for (HitBox hitBox : getHitBoxes()) {
            hitBox.updatePosition(velocity.getX(), velocity.getY());
        }
    }

    /**
     * Update the position and size of the hitbox.
     *
     * @param xPos   the new x position for the hitbox.
     * @param yPos   the new y position for the hitbox.
     * @param width  the new width for the hitbox.
     * @param height the new height for the hitbox.
     * @authors Irja & Viktor
     */
    protected void updateHitBoxes(double xPos, double yPos, double width, double height) {
        for (HitBox hitBox : hitBoxes) {
            hitBox.updateHitBox(xPos, yPos, width, height);
        }
    }

    /**
     * @param className the type of object this object has collided with
     * @author Viktor Sundberg
     */
    @Override
    public void actOnCollision(String className, int amount) {
        if (className.equals("LaserBeam") || className.equals("Spaceship")) {
            this.setCollided(true);
        }
    }

    // Getters and setters -------------------------------------------------------

    /**
     * Updates width both for the object and its hitBoxes
     *
     * @param width the new width
     * @author Tobias Engblom
     */
    public void setWidthHitBoxes(double width) {
        for (HitBox hitBox : hitBoxes) {
            hitBox.getHitBox().setWidth(width);
        }
    }

    /**
     * Updates height both for the object and its hitBoxes
     *
     * @param height the new height
     * @author Tobias Engblom
     */
    public void setHeightHitBoxes(double height) {
        for (HitBox hitBox : hitBoxes) {
            hitBox.getHitBox().setHeight(height);
        }
    }

    /**
     * @return the height of the first hitBox
     * @author Irja & Viktor
     */
    public double getHeight() {
        return hitBoxes.get(0).getHeight();

    }

    /**
     * @return the width of the first hitBox
     * @author Irja & Viktor
     */
    public double getWidth() {
        return hitBoxes.get(0).getWidth();
    }

    /**
     * @return the hitBoxes for this game object
     * @author Tobias Engblom
     */
    public List<HitBox> getHitBoxes() {
        return this.hitBoxes;
    }

    /**
     * @author Viktor Sundberg (viktor.sundberg@icloud.com)
     */
    @Override
    public boolean getCollided() {
        return this.collided;
    }

    @Override
    public void setCollided(boolean b) {
        this.collided = b;
    }

    /**
     * @return the speed of this game object
     * @author Isak Almeros
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Sets the new speed to this game object
     *
     * @param speed the new speed
     * @author Isak Almeros
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public abstract int getAmount();

    @Override
    public abstract String toString();

}
