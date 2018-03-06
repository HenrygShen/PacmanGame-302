public abstract class GameObject {
	
	protected Rectangle hitBox;
	
	
    protected Rectangle getHitBox() {
    	
    	return this.hitBox;
    }

    protected double getX() {
    	
    	return this.hitBox.getX();
    }
    
    protected double getY() {
    	
    	return this.hitBox.getY();
    }
    
    protected void setInvisible() {
    	
    }
    
    
    protected int getType() {
    	return -1;
    }
}
