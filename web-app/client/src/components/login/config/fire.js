import firebase from 'firebase';

  // Your web app's Firebase configuration
  // For Firebase JS SDK v7.20.0 and later, measurementId is optional
  var firebaseConfig = {
    apiKey: "AIzaSyAkqGbQZAcVlTXcApYr5GQc8vUT_Yst3Vo",
    authDomain: "neki-5e90f.firebaseapp.com",
    databaseURL: "https://neki-5e90f.firebaseio.com",
    projectId: "neki-5e90f",
    storageBucket: "neki-5e90f.appspot.com",
    messagingSenderId: "919940426146",
    appId: "1:919940426146:web:66d5212ec48db93c44aafb",
    measurementId: "G-Y0X78VVXNN"
  };
  // Initialize Firebase
  const fire=firebase.initializeApp(firebaseConfig);
  export default fire;
  