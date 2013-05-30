package web.shedule.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProfessorShedule {
	private String name;
	private int id;
	private List<String> listRooms;
	private Map<Integer,String> mapRoom;

	public ProfessorShedule() {
		listRooms=new LinkedList<String>();
		mapRoom=new HashMap<Integer, String>();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setListRooms(List<String> listRooms) {
		this.listRooms = listRooms;
	}

	public List<String> getListRooms() {
		return listRooms;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void mapRoom(int slotId,String roomName)
	{
		mapRoom.put(slotId, roomName);
	}
	
	public void mapRoomToList(int nbSlot)
	{
       for(int i=0;i<nbSlot;i++)
       {
    	   String roomName=mapRoom.get(i);
    	   if(roomName!=null)
    	   {
    		   listRooms.add(roomName);
    	   }
    	   else
    	   {
    		   listRooms.add("");
    	   }
       }
	}
}
