import java.util.ArrayList;

public class Bishop extends Piece
{
  //fields
  int value = 3;
  int type = 4; 
  String player, location;
  
  //constructor
  public Bishop(String player, String location)
  {
    this.player = player;
    this.location = location;
  }
  
  //getter methods***************
  
  public boolean checked(ArrayList<Piece> pieces)
  {
    return false;
  }
  
  public int getValue()
  {
    return value;
  }
  
  public String getPlayer()
  {
    return player;
  }
  
  public String getLocation()
  {
    return location;
  }
  
  public int getType()
  {
    return type;
  }
  
  //methods
  public ArrayList<Move> getPossibleMoves(Game game)
  {
    ArrayList<Piece> pieces = game.getLayout();
    ArrayList<Move> moves = new ArrayList<Move>();
    
    //Up Right
    moves = getUpRight(moves, pieces, location);
    //Up Left
    moves = getUpLeft(moves, pieces, location);
    //Down Right
    moves = getDownRight(moves, pieces, location);
    //Down Left
    moves = getDownLeft(moves, pieces, location);
    
    ArrayList<Move> safeMoves = new ArrayList<Move>();
    
    for(Move move : moves)
    {
      if(player.equals("H")) //human piece
      {
        if(!Chess.newGame(game, move).isWhiteInCheck())
        {
          safeMoves.add(move);
        }
      }
      else //computer piece
      {
         if(!Chess.newGame(game, move).isBlackInCheck())
         {
           safeMoves.add(move);
         }
      }
    }
    return safeMoves;
  }
  
  public String toString()
  {
    return player+"B"+location;
  }
  
  //private methods************
  
  //sees if location is "valid" (inside chess board)
  private boolean isValid(String loc)
  {
    if(loc.charAt(0)<'A'||loc.charAt(0)>'H')
    {
      return false;
    }
    if(loc.charAt(1)<'1'||loc.charAt(1)>'8')
    {
      return false;
    }
    return true;
  }
  
  //sees if a location is occupied by a blank tile (0), a friendly piece (1), an enemy piece (2), or is an invalid location (-1)
  public int landsOn(String loc, ArrayList<Piece> pieces)
  {
    if(!isValid(loc))
    {
      return -1;
    }
    
    for(Piece p : pieces)
    {
      if(p.getLocation().equals(loc))
      {
        if(p.getPlayer().equals(player))
        {
          return 1;
        }
        else
        {
          return 2;
        }
      }
    }
    return 0;
  }
  
  //Up Right
  private ArrayList<Move> getUpRight(ArrayList<Move> moves, ArrayList<Piece> pieces, String start)
  {
    //set new test location
    String test = "" + (char)(start.charAt(0)+1) + (char)(start.charAt(1)+1);
    
    //base
    if(landsOn(test, pieces)==-1||landsOn(test, pieces)==1)
    {
      return moves;
    }
    else
    {
      if(landsOn(test, pieces)==0)
      {
        moves.add(newMove(test));
        return getUpRight(moves, pieces, test);
      }
      else //2
      {
        moves.add(newMove(test));
        return moves;
      }
    }
  }
  
  //Up Left
  private ArrayList<Move> getUpLeft(ArrayList<Move> moves, ArrayList<Piece> pieces, String start)
  {
    //set new test location
    String test = "" + (char)(start.charAt(0)-1) + (char)(start.charAt(1)+1);
    
    //base
    if(landsOn(test, pieces)==-1||landsOn(test, pieces)==1)
    {
      return moves;
    }
    else
    {
      if(landsOn(test, pieces)==0)
      {
        moves.add(newMove(test));
        return getUpLeft(moves, pieces, test);
      }
      else //2
      {
        moves.add(newMove(test));
        return moves;
      }
    }
  }
  
  //Down Right
  private ArrayList<Move> getDownRight(ArrayList<Move> moves, ArrayList<Piece> pieces, String start)
  {
    //set new test location
    String test = "" + (char)(start.charAt(0)+1) + (char)(start.charAt(1)-1);
    
    //base
    if(landsOn(test, pieces)==-1||landsOn(test, pieces)==1)
    {
      return moves;
    }
    else
    {
      if(landsOn(test, pieces)==0)
      {
        moves.add(newMove(test));
        return getDownRight(moves, pieces, test);
      }
      else //2
      {
        moves.add(newMove(test));
        return moves;
      }
    }
  }
  
  //Down Left
  private ArrayList<Move> getDownLeft(ArrayList<Move> moves, ArrayList<Piece> pieces, String start)
  {
    //set new test location
    String test = "" + (char)(start.charAt(0)-1) + (char)(start.charAt(1)-1);
    
    //base
    if(landsOn(test, pieces)==-1||landsOn(test, pieces)==1)
    {
      return moves;
    }
    else
    {
      if(landsOn(test, pieces)==0)
      {
        moves.add(newMove(test));
        return getDownLeft(moves, pieces, test);
      }
      else //2
      {
        moves.add(newMove(test));
        return moves;
      }
    }
  }
  
  //creates string for location
  private Move newMove(String to)
  {
    //return player + "B" + location + " to " + loc;
    return new Move(new String[]{location}, new String[]{to}, type, player);
  }
  
}
