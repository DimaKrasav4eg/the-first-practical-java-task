package third_mission;

import java.time.LocalDate;
import java.util.ArrayList;

public class PersonAdress {

	public static void main(String[] args) {
		Adress adr = new Adress("Baker Street", "221B");
		PersonAdress persons = 
			new PersonAdress(new Person(  "Patel",  LocalDate.of(1977, 11, 24), new Adress(   "Kingfield",    "9") ),
				             new Person(  "Smith",  LocalDate.of(2000,  5, 15), new Adress("Brenthem Way",   "36",  "5") ),
				             new Person(  "Smith",  LocalDate.of(1999,  6,  6), new Adress(   "Mount Ave",    "4", "14") ),
				             new Person(  "Jones",  LocalDate.of(1978,  8,  1), new Adress(   "Kingfield",   "13", "10") ),
				             new Person( "Holmes",  LocalDate.of(1887,  9, 17), new Adress("Baker Street", "221B") ));
		
		PersonAdress[] personSearch = {new PersonAdress(persons.searchOneSurname("Smith")),
		                               new PersonAdress(persons.searchCommonAdress(adr)),
		                               new PersonAdress(persons.searchByBirthDate(LocalDate.of(1970, 1, 1), LocalDate.of(2000, 1, 1))),
		                               new PersonAdress(persons.searchOldest()),
		                               new PersonAdress(persons.searchYoungest()),
		                               new PersonAdress(persons.searchCommonStreet("Kingfield"))};
		
		for (int i = 0; i < personSearch.length; ++i) {
			System.out.printf("Task (%d)\n", i + 1);
			personSearch[i].printMoreOnePerson();
			System.out.println();
		}
		
	}
	/**
	 * содержит данные людей
	 */
	private Person[] persons;
	
	public PersonAdress(Person ...persons) {
		this.persons = persons;
	}
	public PersonAdress(ArrayList<Person> persons) {
		this.persons = new Person[persons.size()];
		for (int i = 0; i < persons.size(); ++i) {
			this.persons[i] = persons.get(i);
		}
	}
	/**
	 * Поиск людей с одинаковой фамилией
	 * @param surname искомая фамилия
	 * @return soughtPerson результат поиска
	 */
	public ArrayList<Person> searchOneSurname(String surname) {
		ArrayList<Person> soughtPerson = new ArrayList<Person>();
		
		for (Person item : this.persons) {
			if (item.surname == surname) {
				soughtPerson.add(item);
			}
		}
		return soughtPerson;
	}
	/**
	 * Поиск людей, родившихся между определенными датами
	 * @param firstPoint первая дата
	 * @param secondPoint вторая дата
	 * @return soughtPerson результат поиска 
	 */
	public ArrayList<Person> searchByBirthDate(LocalDate firstPoint, LocalDate secondPoint) {
		ArrayList<Person> soughtPerson = new ArrayList<Person>();
		
		for (Person item : this.persons) {
			if (item.birthDate.isAfter(firstPoint) && item.birthDate.isBefore(secondPoint)) {
				soughtPerson.add(item);
			}
		}
		return soughtPerson;
	}
	/**
	 * Поиск самого старшего человека
	 * @return oldest результат поиска
	 */
	public Person searchOldest() {
		Person oldest = persons[0];
		for (Person item : this.persons) {
			if (item.birthDate.isBefore(oldest.birthDate)) {
				oldest = item;
			}
		}
		return oldest;
	}
	/**
	 * Поиск самого молодого человека
	 * @return youangest результат поиска
	 */
	public Person searchYoungest() {
		Person youangest = persons[0];
		for (Person item : this.persons) {
			if (item.birthDate.isAfter(youangest.birthDate)) {
				youangest = item;
			}
		}
		return youangest;
	}
	/**
	 * Осуществляет поиск людей с одинаковыми адресами
	 * @param adress адрес, по коророму происходит поиск
	 * @return soughtPerson результат поиска
	 */
	public ArrayList<Person> searchCommonAdress(Adress adress) {
		ArrayList<Person> soughtPerson = new ArrayList<Person>();
		
		for (Person item : this.persons) {
			if (item.adress.isOne(adress)) {
				soughtPerson.add(item);
			}
		}
		return soughtPerson;
	}
	/**
	 * Осуществляет поиск людей с одинаковой улицей
	 * @param street улица, по которой происходит поиск
	 * @return soughtPerson результат поиска
	 */
	public ArrayList<Person> searchCommonStreet(String street) {
		ArrayList<Person> soughtPerson = new ArrayList<Person>();
		
		for (Person item : this.persons) {
			if (item.adress.street == street) {
				soughtPerson.add(item);
			}
		}
		return soughtPerson;
	}
	/**
	 * Печатает в консоль массив из Person
	 */
	public void printMoreOnePerson() {
		for (Person item : this.persons) {
			item.print();
			System.out.println();
		}
	}
	
	/**
	 * Класс, содержащий адрес
	 */
	public static class Adress {
		public String street = "";
		public String home   = "";
		public String flat   = "";
		
		public Adress(String street, String home, String flat) {
			this.street = street;
			this.home   = home;
			this.flat   = flat;
		}
		public Adress(String street, String home) {
			this.street = street;
			this.home   = home;
		}
		/**
		 * Возвращает строку адреса
		 * @return строка адреса
		 */
		public String getAdress() {
			return String.join(" ", this.street, this.home, this.flat);
		}
		/**
		 * осуществляет сравнение адресов
		 * @param adress 
		 * @return результат сравнения
		 */
		public boolean isOne(Adress adress) {
			if (this.street == adress.street 
					&& this.home == adress.home 
					&& this.flat == adress.flat) {
				return true;
			}
			return false;
		}
		/**
		 * Печать адреса в консоль
		 */
		public void print() {
			System.out.printf("%s %s %s", street, home, flat);
		}
	}
	/**
	 * класс, содержащий данные об одном человеке
	 */
	public static class Person {
		String surname;
		LocalDate birthDate;
		Adress adress;
		public Person(String surname, LocalDate birthDate, Adress adress) {
			this.surname   = surname;
			this.birthDate = birthDate;
			this.adress    = adress;
		}
		/**
		 * Печатает данные человака
		 */
		public void print() {
			System.out.printf("%s:\nbirthdate %s\nadress    ", surname, birthDate);
			this.adress.print();
		}
	}

}
