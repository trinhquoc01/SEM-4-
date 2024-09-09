import 'package:flutter/material.dart';
import 'pages/order_list_page.dart';
import 'pages/add_order_page.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Head Chef',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Wellcome Head Chef'),
      ),
      drawer: Drawer(
        child: ListView(
          padding: EdgeInsets.zero,
          children: <Widget>[
            DrawerHeader(
              decoration: BoxDecoration(
                color: Colors.green,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  CircleAvatar(
                    radius: 30,
                    backgroundColor: Colors.white,
                    child: Icon(Icons.person, size: 50),
                  ),
                  SizedBox(height: 10),
                  Text(
                    'Head Chef',
                    style: TextStyle(color: Colors.white, fontSize: 20),
                  ),
                  Text(
                    'HeadChef@gmail.com',
                    style: TextStyle(color: Colors.white, fontSize: 14),
                  ),
                ],
              ),
            ),
            ListTile(
              leading: Icon(Icons.home),
              title: Text('Home'),
              onTap: () {},
            ),
            ListTile(
              leading: Icon(Icons.restaurant),
              title: Text('Restaurants'),
              onTap: () {},
            ),
            ListTile(
              leading: Icon(Icons.list),
              title: Text('Orders'),
              onTap: () {
                Navigator.push(context,
                    MaterialPageRoute(builder: (context) => OrderListPage()));
              },
            ),
            ListTile(
              leading: Icon(Icons.settings),
              title: Text('Settings'),
              onTap: () {},
            ),
            ListTile(
              leading: Icon(Icons.info),
              title: Text('About Me'),
              onTap: () {},
            ),
          ],
        ),
      ),
      body: Center(
        child: Text('Wellcome Head Chef'),
      ),
    );
  }
}
