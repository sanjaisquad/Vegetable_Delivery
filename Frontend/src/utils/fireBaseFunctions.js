import {
  collection,
  doc,
  getDocs,
  orderBy,
  query,
  setDoc,
} from "firebase/firestore";
import { firestore } from "../firebase.config";

// Sending new Item to firebase

export const sendItem = async (data) => {
    await setDoc(doc(firestore, "foodItems", `${Date.now()}`), data, {
        merge: true,
    });
};

//get all food items from firebase 

export const getAllFoodItems2 = async () => {

  const items = await getDocs(
    query(collection(firestore, "foodItems"), orderBy("id", "desc"))
  );
  return items.docs.map((doc) => doc.data());
}

// export const getAllFoodItems = async () => {
//   try {
//     const response = await fetch('http://localhost:8080/api/v1/products', {
//       method: 'GET'
//     });

//     if (!response.ok) {
//       throw new Error('Failed to fetch data');
//     }
    
//     const data = await response.json();
    
//     return data;
//   } catch (error) {
//     console.error('Error fetching data:', error);
//     return []; // Return an empty array in case of error
//   }
// }


