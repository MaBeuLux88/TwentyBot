package com.beugnet;

public class Tile {
    private Coordinate center;
    private Integer value;
    private boolean hasLinkLeft;
    private boolean hasLinkRight;
    private boolean hasLinkDown;
    private boolean hasLinkUp;

    public Tile(Coordinate center, Integer value, boolean hasLinkLeft, boolean hasLinkRight, boolean hasLinkDown, boolean hasLinkUp) {
        this.center = center;

        this.value = value;
        this.hasLinkLeft = hasLinkLeft;
        this.hasLinkRight = hasLinkRight;
        this.hasLinkDown = hasLinkDown;
        this.hasLinkUp = hasLinkUp;
    }

    public Coordinate getCenter() {
        return center;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (hasLinkLeft != tile.hasLinkLeft) return false;
        if (hasLinkRight != tile.hasLinkRight) return false;
        if (hasLinkDown != tile.hasLinkDown) return false;
        if (hasLinkUp != tile.hasLinkUp) return false;
        return value != null ? value.equals(tile.value) : tile.value == null;

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (hasLinkLeft ? 1 : 0);
        result = 31 * result + (hasLinkRight ? 1 : 0);
        result = 31 * result + (hasLinkDown ? 1 : 0);
        result = 31 * result + (hasLinkUp ? 1 : 0);
        return result;
    }

    public Integer getValue() {
        return value;
    }

    public boolean hasLinkLeft() {
        return hasLinkLeft;
    }

    public boolean hasLinkRight() {
        return hasLinkRight;
    }

    public boolean hasLinkDown() {
        return hasLinkDown;
    }

    public boolean hasLinkUp() {
        return hasLinkUp;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("{").append(center.getX()).append(";").append(center.getY()).append("} ");
        output.append(value);
        if (hasLinkLeft)
            output.append(" LEFT");
        if (hasLinkRight)
            output.append(" RIGHT");
        if (hasLinkDown)
            output.append(" DOWN");
        if (hasLinkUp)
            output.append(" UP");
        return output.toString();
    }
}
