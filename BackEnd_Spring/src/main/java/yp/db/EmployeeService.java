package yp.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class EmployeeService {

	public static final String COL_NAME = "employees";
	
	public String saveEmployeeDetails(Employee employee) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(employee.getName())
				.set(employee);

		return collectionsApiFuture.get().getUpdateTime().toString();

	}
	
	public Employee getEmployeeDetails(String name) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
//		DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
//		ApiFuture<DocumentSnapshot> future = documentReference.get();
//		DocumentSnapshot document = future.get();

		DocumentSnapshot document = dbFirestore.collection(COL_NAME).document(name).get().get();

		Employee employee = null;

		if (document.exists()) {
			employee = document.toObject(Employee.class);
			return employee;
		} else {
			return null;
		}
	}

	public static Iterable<DocumentReference> getAllDetailsIterable() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		return dbFirestore.collection(COL_NAME).listDocuments();

	}
	
	public List<Employee> getAllDetails() throws InterruptedException, ExecutionException {
		List<Employee> pList = new ArrayList<Employee>();
		Iterable<DocumentReference> itr = getAllDetailsIterable();
		
		for (DocumentReference d : itr)
			pList.add(d.get().get().toObject(Employee.class));
		
		return pList;
	}

	public String updateEmployeeDetails(Employee person) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(person.getName())
				.set(person);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}

	public String deleteEmployee(String name) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
		return "Document with Employee ID " + name + " has been deleted";
	}
	
}
