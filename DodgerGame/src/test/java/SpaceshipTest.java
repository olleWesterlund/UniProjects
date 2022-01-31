import Model.CollisionHandler;
import Model.Entities.AbstractGameObject;
import Model.Entities.HitBox;
import Model.Entities.Player.Spaceship;
import Model.SpaceshipFactory;
import Model.Entities.Projectiles.Projectile;
import Model.ProjectileFactory;
import Model.GameWorld;
import Model.Entities.Point2D;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class SpaceshipTest {

    private CollisionHandler collisionHandler = new CollisionHandler();
    private List<AbstractGameObject> gameObjects = new ArrayList<>();
    private Spaceship spaceship;
    private Projectile healthPowerUp = ProjectileFactory.createHealthPowerUp(20, 20, 20, 20, 20);
    private Projectile shieldPowerUp = ProjectileFactory.createShieldPowerUp(20, 20, 20, 20, 20);
    private Projectile slowDebuff = ProjectileFactory.createSlowDebuff();
    private Projectile asteroid = ProjectileFactory.createAsteroid(20, 20, 20, 20, 20, 20, 20, 20);
    private Projectile laserBeam = ProjectileFactory.createLaserBeam(3);
    private Point2D startPos;
    private double deltaTime = 0.016;

    /**
     * creates a spaceship for testing
     */
    @Before
    public void init() {
        spaceship = SpaceshipFactory.createSpaceship(0, 0, 64, 64);
    }

    /**
     * Tests if the spaceship can move its position to the left
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedLeft() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setLeft(1);
        // Negative x value to move to the left
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getX() < startPos.getX());
    }

    /**
     * Tests if the spaceship can move its position to the right
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedRight() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setRight(1);
        // Positive x value to move to the right
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getX() > startPos.getX());
    }

    /**
     * Tests if the spaceship can move its position up
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUp() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setUp(1);
        // Negative y value to move up
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getY() < startPos.getY());
    }

    /**
     * Tests if the spaceship can move its position down
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDown() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setDown(1);
        // Positive y value to move down
        spaceship.move(deltaTime);
        assertTrue(spaceship.getHitBoxes().get(0).getY() > startPos.getY());
    }

    /**
     * Tests if the spaceship can move its position up and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setUp(1);
        spaceship.setRight(1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() > startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() < startPos.getY()));
    }

    /**
     * Tests if the spaceship can move its position up and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedUpAndLeft() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setUp(1);
        spaceship.setLeft(1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() < startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() < startPos.getY()));
    }

    /**
     * /**
     * Tests if the spaceship can move its position down and right diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setDown(1);
        spaceship.setRight(1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() > startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() > startPos.getY()));
    }

    /**
     * Tests if the spaceship can move its position down and left diagonally
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipMovedDownAndLeft() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setDown(1);
        spaceship.setLeft(1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() < startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() > startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move left and right simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenLeftAndRight() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setLeft(1);
        spaceship.setRight(1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() == startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when attempting to move up and down simultaneously
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenUpAndDown() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.setUp(1);
        spaceship.setDown(1);
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() == startPos.getY()));
    }

    /**
     * Tests if the spaceship stays in place when its velocity is zero
     *
     * @author Irja Vuorela
     */
    @Test
    public void spaceshipNotMovingWhenVelocityZero() {
        startPos = spaceship.getHitBoxes().get(0).getHitBox().getPosition();
        spaceship.move(deltaTime);
        assertTrue((spaceship.getHitBoxes().get(0).getX() == startPos.getX()) && (spaceship.getHitBoxes().get(0).getY() == startPos.getY()));
    }

    /**
     * @authors Viktor & Irja
     */
    @Test
    public void testGainShield() {
        int oldShields = spaceship.getNrOfShields();
        Spaceship spaceship = SpaceshipFactory.createSpaceship(10, 10, 10, 10);
        Projectile shieldPowerUp = ProjectileFactory.createShieldPowerUp(0, 10, 10, 0, 0);
        gameObjects.clear();
        gameObjects.add(shieldPowerUp);
        gameObjects.add(spaceship);
        collisionHandler.handleCollision(gameObjects);
        int newShields = spaceship.getNrOfShields();
        assertTrue(newShields > oldShields);
    }

    /**
     * Test that the health power up increases the health.
     *
     * @author Olle Westerlund, Irja Vuorela, Viktor Sundberg
     */
    @Test
    public void testGainHealth() {
        Spaceship spaceship = SpaceshipFactory.createSpaceship(10, 10, 10, 10);
        Projectile healthPowerUp = ProjectileFactory.createHealthPowerUp(0, 10, 10, 0, 0);
        spaceship.setHp(spaceship.getHp()-1);
        int oldHealth = spaceship.getHp();
        spaceship.actOnCollision("HealthPowerUp", healthPowerUp.getAmount());
        int newHealth = spaceship.getHp();
        assertTrue(newHealth > oldHealth);
    }

    /**
     * Checks that player can't heal beyond the set max hp value
     *
     * @authors Irja & Viktor
     */
    @Test
    public void didNotExceedMaxHp() {
        Spaceship spaceship = SpaceshipFactory.createSpaceship(10,10,10,10);
        Projectile healthPowerUp = ProjectileFactory.createHealthPowerUp(0,10,10,0,0);

        spaceship.setHp(spaceship.getMaxHp() - 1);
        spaceship.actOnCollision(healthPowerUp.toString(), healthPowerUp.getAmount());
        assertEquals(spaceship.getHp(), spaceship.getMaxHp());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithHealthPowerUpGainHealth() {
        spaceship.setHp(150);
        spaceship.actOnCollision(healthPowerUp.toString(), healthPowerUp.getAmount());
        assertEquals(spaceship.getHp(), 200);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithShieldPowerUpGainShield() {
        int oldNrOfShields = spaceship.getNrOfShields();
        spaceship.actOnCollision(shieldPowerUp.toString(), shieldPowerUp.getAmount());
        int newNrOfShields = spaceship.getNrOfShields();
        assertTrue(newNrOfShields > oldNrOfShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithSlowDebuffSpeedDecreased() {
        double oldSpeed = spaceship.getSpeed();
        spaceship.actOnCollision(slowDebuff.toString(), slowDebuff.getAmount());
        double newSpeed = spaceship.getSpeed();
        assertTrue(newSpeed < oldSpeed);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void collideWithAsteroidHealthDecreased() {
        double oldHp = spaceship.getHp();
        spaceship.actOnCollision(asteroid.toString(), asteroid.getAmount());
        double newHp = spaceship.getHp();
        assertTrue(newHp < oldHp);
    }

    /**
     * @author Tobias Engblom & Irja Vuorela
     */
    @Test
    public void collideWithAsteroidLoseShield() {
        Spaceship spaceship = SpaceshipFactory.createSpaceship(10, 10, 10, 10);
        Projectile shieldPowerUp = ProjectileFactory.createShieldPowerUp(0, 10, 10, 0, 0);
        Projectile asteroid = ProjectileFactory.createAsteroid(0, 10, 10, 10, 10, 0, 0, 1);
        spaceship.actOnCollision(shieldPowerUp.toString(), shieldPowerUp.getAmount());
        int oldNrOfShields = spaceship.getNrOfShields();
        spaceship.actOnCollision(asteroid.toString(), asteroid.getAmount());
        int newNrOfShields = spaceship.getNrOfShields();
        assertTrue(newNrOfShields < oldNrOfShields);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void testLoseShieldWhenNrShieldsIsZero() {
        Spaceship spaceship = SpaceshipFactory.createSpaceship(10, 10, 10, 10);
        Projectile shieldPowerUp = ProjectileFactory.createShieldPowerUp(0, 10, 10, 0, 0);
        Projectile asteroid = ProjectileFactory.createAsteroid(0, 10, 10, 10, 10, 0, 0, 1);
        int oldShields = spaceship.getNrOfShields();
        spaceship.actOnCollision(shieldPowerUp.toString(), shieldPowerUp.getAmount());
        spaceship.actOnCollision(asteroid.toString(), asteroid.getAmount());
        int newShields = spaceship.getNrOfShields();
        assertEquals(newShields, oldShields);
    }

    /**
     * @author Olle Westerlund
     */
    @Test
    public void collisionWithLaserBeam() {
        int health = spaceship.getHp();
        spaceship.actOnCollision(laserBeam.toString(), laserBeam.getAmount());
        int currentHealth = spaceship.getHp();
        assertTrue(currentHealth < health);
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void westWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(-20, 300, 64, 64);
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void northWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, -5, 64, 64);
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void eastWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(770, 300, 64, 64);
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void southWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, 550, 64, 64);
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(2, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsWestWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(-20, 300, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(-20, 300, 64, 64));
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsNorthWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(20, -5, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(20, -5, 64, 64));
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsEastWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(770, 300, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(770, 300, 64, 64));
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Tobias Engblom
     */
    @Test
    public void twoSpaceshipsSouthWrapAround() {
        spaceship.getHitBoxes().get(0).updateHitBox(5, 550, 64, 64);
        spaceship.getHitBoxes().add(new HitBox(5, 550, 64, 64));
        GameWorld.getInstance().wrapAround(spaceship);
        assertEquals(4, spaceship.getHitBoxes().size());
    }

    /**
     * @author Olle Westerlund
     */
    @Test
    public void updateHitBoxSize() {
        HitBox hitbox = spaceship.getHitBoxes().get(0);
        double width = hitbox.getWidth();
        double height = hitbox.getHeight();
        spaceship.getHitBoxes().get(0).updateHitBoxSize(width * 2, height * 2);
        double newWidth = hitbox.getWidth();
        double newHeight = hitbox.getHeight();
        assertTrue(newWidth == (width * 2) && newHeight == (height * 2));
    }
}
