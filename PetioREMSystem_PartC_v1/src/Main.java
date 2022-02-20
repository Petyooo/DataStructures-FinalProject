/*
 * CurMain.java
 * Petio Todorov
 * 18/1/20
 * 
 * My current main file. It has been used for testing.
 * Uncomment or comment the following to test the Real Estate Management System: 
 * 1. test print houses and apartments
 * 2. test search on price
 * 3. test search on bedrooms and price
 * 4. test Search on Distance
 * 5. test printing of regular and vip clients
 * 6. test request and organize visit method
 * 
 * The following code is just for me to test some of the data
 * structures.
 * 7. test circular vector
 * 8. test Queue based on Circular Vector
 * 9. test vector
 * 10. test sorted vector
 * 11. test out Dictionary
 * 12. test Tree example one- super right sided tree
 * 13. test Tree- another example.
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create a new Real Estate Management System
		IManagementSystem test = new REMSystem();
		
		//Add Houses and Apartments
		test.addNewHouse("First Street", 4, 5, 0);
		test.addNewHouse("Second Street", 4, 5, 900);
		test.addNewHouse("Third Street", 1, 2, 300);
		test.addNewHouse("Fourth Street", 4, 5, 700);
		test.addNewApartment("Fifth Street", 2, 5, 50, true, 2, 6);
		test.addNewApartment("Sixth Street", 2, 4, 800, true, 2, 6);
		test.addNewApartment("Seventh Street", 2, 3, 800, true, 2, 6);
		test.addNewApartment("Eigth Street", 2, 5, 1000, true, 2, 6);
		test.addNewApartment("Ninth Street", 2, 5, 2000, true, 2, 6);
		test.addNewApartment("Tenth Street", 6, 5, 50, true, 2, 6);
		test.addNewApartment("Eleventh Street", 6, 5, 2500, true, 2, 6);
		
		//Add streets
		test.addStreet("First Street");
		test.addStreet("Second Street");
		test.addStreet("Third Street");
		test.addStreet("Fourth Street");
		test.addStreet("Fifth Street");
		test.addStreet("Sixth Street");
		test.addStreet("Seventh Street");
		test.addStreet("Eigth Street");
		test.addStreet("Ninth Street");
		test.addStreet("Tenth Street");
		test.addStreet("Eleventh Street");
		
		//Add edges with distance in km as the weight
		test.connectStreets("Second Street", "First Street", 2.0);
		test.connectStreets("Second Street", "Fourth Street", 4.0);
		test.connectStreets("Second Street", "Third Street", 3.0);

		//up to here the estates have taken id's 1-11 
		//6 new clients will be added with id #'s 12-17
		test.addRegularClient("Petio Todorov", "petio19@gmail.com");
		test.addRegularClient("Joe Schmoe", "joey@gmail.com");

		test.addVIPClient("David Beckham", "ManUnitedSucks@epl.com");
		test.addVIPClient("Thierry Henry", "LetsGoGunners@arsenal.com");
		test.addVIPClient("Zinedine Zidane", "TheGreatestOfThemAll@fifa.com");
		test.addVIPClient("Ronaldinho", "EquallyGreatAsZidane@fifa.com");
		
		//1. Test print houses and apartments
		test.printHouses();
		test.printApartments();
		
		//2. test Search on price
		test.searchOnPrice(0, 1000);
		test.searchOnPrice(1000, 2000);
		test.searchOnPrice(5000, 6000);			//check when none are within price range
		
		//3. test Search on Bedrooms and price
		test.searchOnBedroomsAndPrice(2, 1000);
		test.searchOnBedroomsAndPrice(6, 2000);
		test.searchOnBedroomsAndPrice(10, 0);			//check when none are within price range or bedrooms

		//4. test Search on Distance
		test.searchOnDistance("Second Street", 4.0);

		//5. test printing of clients and VIP clients
		test.printRegularClients();
		test.printVIPClient();
		
		//6. test the request and organize visit method
		test.requestVisit(12, 1);
		test.requestVisit(13, 1);
		test.requestVisit(14, 1);
		test.requestVisit(15, 1);
		test.requestVisit(16, 1);
		
		test.requestVisit(12, 2);
		test.requestVisit(13, 2);
		
		test.organizeVisits();

		
		//7. test circular vector
//		CircularVector cvect = new CircularVector();
//		cvect.AddFirst(1);
//		cvect.AddFirst(2);
//		cvect.AddLast(3);
//		cvect.AddLast(4);
//		cvect.AddFirst(5);
//		cvect.AddLast(6);
//		cvect.AddLast(7);
//
//		cvect.print();
//		cvect.RemoveFirst();
//		cvect.RemoveLast();
//
//		cvect.print();
//		
//		System.out.println(cvect.GetFirst());
//		System.out.println(cvect.GetLast());
//		System.out.println(cvect.IsEmpty());

		//8. test Queue based on Circular Vector
//		Queue cvQueue = new Queue();
//		cvQueue.push(1);
//		cvQueue.push(2);
//		cvQueue.push(3);
//		cvQueue.push(4);
//		System.out.println(cvQueue.toString());
//		System.out.println(cvQueue.pop());
//		System.out.println(cvQueue.top());
		
//		//9. test vector
//		Vector v = new Vector(10);
//		v.addFirst(1);
//		v.addFirst(2);
//		v.addLast(3);
//		v.addLast(4);
//		v.addFirst(5);
//		v.addLast(6);
//		v.addLast(7);
//		v.set(0,100);
//		System.out.println(v.toString());

//		//10. test sorted vector
//		SortedVector v = new SortedVector(10);
//		v.addSorted(1);
//		v.addSorted(2);
//		v.addSorted(3);
//		v.addSorted(4);
//		v.addSorted(6);
//		v.addSorted(7);
//
//		System.out.println(v.toString());
//		System.out.println(v.binarySearchForIndex(4));

		//11. Testing out Dictionary
//		Dictionary testDic = new Dictionary();
//		testDic.add(0, "Peter");
//		testDic.add(2, "John");
//		testDic.add(3, "Mary");
//		testDic.add(5, "Jay");
//		
//		System.out.println(testDic.toString());
//		System.out.println(testDic.find(2));
//		System.out.println(testDic.findPosition(5));
//		System.out.println(testDic.findPosition(10));
//		System.out.println(testDic.find(8));
		
		//Testing out Tree
		//12. example one- super right sided tree

//		Tree myTree = new Tree();
//		myTree.insert(1);
//		myTree.insert(2);
//		myTree.insert(3);
//		myTree.insert(4);
//		myTree.insert(5);
//		myTree.insert(6);
//		myTree.print();
//		System.out.println(myTree.depth());
//		System.out.println(myTree.largestElm());
//		
//		System.out.println(myTree.find(1));
//		System.out.println(myTree.find(2));
//		System.out.println(myTree.find(3));
//		System.out.println(myTree.find(4));
//		System.out.println(myTree.find(5));
//		System.out.println(myTree.find(6));
//		System.out.println(myTree.find(7));
//		
		//13. another Tree example.
//		Tree myTree = new Tree();		
//		myTree.insert(10);
//		myTree.insert(4);
//		myTree.insert(15);
//		myTree.insert(12);
//		myTree.insert(18);
//		myTree.insert(19);
//		myTree.print();
//		System.out.println(myTree.depth());
//		
//		System.out.println(myTree.largestElm());
//		System.out.println(myTree.find(10));
//		System.out.println(myTree.find(4));
//		System.out.println(myTree.find(15));
//		System.out.println(myTree.find(12));
//		System.out.println(myTree.find(18));
//		System.out.println(myTree.find(19));
		
	}

}
