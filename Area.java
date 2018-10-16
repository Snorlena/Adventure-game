
public class Area
{
  private int myId;
  private int northNeighbourId;
  private int southNeighbourId;
  private int westNeighbourId;
  private int eastNeighbourId;
  
  public Area(int myId, int northNeighbourId, int southNeighbourId, int westNeighbourId, int eastNeighbourId)
  {
    this.myId = myId;
    this.northNeighbourId = northNeighbourId;
    this.southNeighbourId = southNeighbourId;
    this.westNeighbourId = westNeighbourId;
    this.eastNeighbourId = eastNeighbourId;
  }

  public int getId()
  {
    return this.myId;
  }

  public int getNorthNeighbourId()
  {
    return this.northNeighbourId;
  }

  public int getSouthNeighbourId()
  {
    return this.southNeighbourId;
  }

  public int getWestNeighbourId()
  {
    return this.westNeighbourId;
  }

  public int getEastNeighbourId()
  {
    return this.eastNeighbourId;
  }
  
}
