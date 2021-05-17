package client.network;

import shared.networking.RMIServer;
import shared.networking.RemoteObserver;
import shared.transferobjects.Category;
import shared.transferobjects.City;
import shared.transferobjects.Member;
import shared.transferobjects.State;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIClient implements Client, RemoteObserver
{
  private PropertyChangeSupport support;
  private RMIServer server;

  public RMIClient(){
    support = new PropertyChangeSupport(this);
  }

  @Override public void startClient()
  {
    try
    {
      UnicastRemoteObject.exportObject(this,0);
      Registry registry = LocateRegistry.getRegistry("localhost", 1099);
      server = (RMIServer) registry.lookup("ShareIt");
      server.registerClient(this);
      System.out.println("Client Connected");
    }
    catch (RemoteException | NotBoundException e)
    {
      e.printStackTrace();
    }
  }

  @Override
  public void checkMemberData(String username, String password, String confirmPassword, String email, String phone, String otherInformation, String street, String streetNo, String postalCode, String city) throws IOException {
    try{
      server.checkMemberData(username, password, confirmPassword, email, phone, otherInformation, street, streetNo, postalCode, city);
    }
    catch (RemoteException e){
      e.printStackTrace();
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public void checkRentalData(String name, String pictureLink, String description, String price, String otherInformation, String stateName, Member member) throws IOException {
    try {
      server.checkRentalData(name, pictureLink, description, price, otherInformation, stateName, member);
    }
    catch (RemoteException e){
      e.printStackTrace();
      throw new RuntimeException("Could not contact server");
    }
  }

  @Override
  public ArrayList<City> getCityList() {
    try {
      return server.getCityList();
    }
    catch (RemoteException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public ArrayList<State> getStateList() {
    try{
      return server.getStateList();
    }
    catch (RemoteException e){
      e.printStackTrace();
    }
    return  null;
  }

  @Override
  public ArrayList<Category> getCategoryList() {
    try{
      return server.getCategoryList();
    }
    catch (RemoteException e){
      e.printStackTrace();
    }
    return null;
  }

  @Override public void addListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.addPropertyChangeListener(propertyName, listener);
    else
      support.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(String propertyName,
      PropertyChangeListener listener)
  {
    if(propertyName != null)
      support.removePropertyChangeListener(propertyName, listener);
    else
      support.removePropertyChangeListener(listener);
  }

  @Override public void update(Object object) throws RemoteException
  {
    /*if(object instanceof Message)
    {
      support.firePropertyChange("NewMessage", null, object);
    }
    else if (object instanceof UserName)
    {
      support.firePropertyChange("NewUser", null, object);
    }*/
  }
}
