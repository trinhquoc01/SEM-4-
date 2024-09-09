import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:nhahang/pages/transfer.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(); // Khởi tạo Firebase
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Restaurent',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: TransferPage(),
    );
  }
}