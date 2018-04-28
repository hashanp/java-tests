package aeroplane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class SeatAllocator {

  private Map<Seat, Passenger> allocation;

  private static final String CREW = "crew";
  private static final String BUSINESS = "business";
  private static final String ECONOMY = "economy";

  public SeatAllocator() {
    allocation = new HashMap<Seat, Passenger>();
  }

  @Override
  public String toString() {
    return allocation.toString();
  }

  private void allocateInRange(Passenger passenger,
      Seat first, Seat last) throws AeroplaneFullException {

    while (true) {
      if (!allocation.containsKey(first)) {
        if (!first.isEmergencyExit() || passenger.isAdult()) {
          allocation.put(first, passenger);
          return;
        }
      }
      if (first.equals(last)) {
        break;
      } else {
        first = first.next();
      }
    }

    throw new AeroplaneFullException();

  }

  private static String readStringValue(BufferedReader br) throws MalformedDataException, IOException {

    String result = br.readLine();

    if(result == null) {
      throw new MalformedDataException();
    }

    return result;

  }

  private static int readIntValue(BufferedReader br)
      throws MalformedDataException, IOException {
    try {
      return Integer.parseInt(readStringValue(br));
    } catch(NumberFormatException e) {
      throw new MalformedDataException();
    }
  }

  private static Luxury readLuxuryValue(BufferedReader br)
      throws MalformedDataException, IOException {
    try {
      return Luxury.valueOf(readStringValue(br));
    } catch(IllegalArgumentException e) {
      throw new MalformedDataException();
    }
  }


  public void allocate(String filename) throws IOException, AeroplaneFullException {

    BufferedReader br = new BufferedReader(new FileReader(filename));

    String line;
    while((line = br.readLine()) != null) {
      try {
        if(line.equals(CREW)) {
          allocateCrew(br);
        } else if(line.equals(BUSINESS)) {
          allocateBusiness(br);
        } else if(line.equals(ECONOMY)) {
          allocateEconomy(br);
        } else {
          throw new MalformedDataException();
        }
      } catch(MalformedDataException e) {
        System.out.println("Skipping malformed line of input");
      }
    }

  }

  private void allocateCrew(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
    String firstName = readStringValue(br);
    String lastName = readStringValue(br);
    final Seat first = new Seat(1, 'A');
    final Seat last = new Seat(1, 'F');
    allocateInRange(new CrewPassenger(firstName, lastName), first, last);
  }

  private void allocateBusiness(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
    String firstName = readStringValue(br);
    String lastName = readStringValue(br);
    int age = readIntValue(br);
    Luxury luxury = readLuxuryValue(br);
    final Seat first = new Seat(2, 'A');
    final Seat last = new Seat(15, 'F');
    final Passenger passenger
        = new BusinessClassPassenger(firstName, lastName, age, luxury);
    allocateInRange(passenger, first, last);
  }

  private void allocateEconomy(BufferedReader br) throws IOException, MalformedDataException, AeroplaneFullException {
    String firstName = readStringValue(br);
    String lastName = readStringValue(br);
    int age = readIntValue(br);
    final Seat first = new Seat(16, 'A');
    final Seat last = new Seat(50, 'F');
    final Passenger passenger
        = new EconomyClassPassenger(firstName, lastName, age);
    allocateInRange(passenger, first, last);
  }

  public void upgrade() {
    Seat economyStart = new Seat(16, 'A');
    final Seat economyEnd = new Seat(50, 'F');
    Seat businessStart = new Seat(2, 'A');
    final Seat businessEnd = new Seat(15, 'F');
    while (true) {
      try {
        if (allocation.containsKey(economyStart)) {
          final Passenger passenger = allocation.get(economyStart);
          allocateInRange(passenger, businessStart, businessEnd);
          allocation.remove(economyStart);
        }
        if (economyStart.equals(economyEnd)) {
          break;
        } else {
          System.out.println(economyStart);
          economyStart = economyStart.next();
        }
      } catch(AeroplaneFullException e) {
        break;
      }
    }
  }

}
