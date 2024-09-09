import 'package:flutter/material.dart';
import 'package:nhahang/pages/order_list_page.dart'; // Đảm bảo đúng đường dẫn

class TransferPage extends StatefulWidget {
  @override
  _TransferPageState createState() => _TransferPageState();
}

class _TransferPageState extends State<TransferPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Welcome Restaurant'),
        backgroundColor: Color(0xFF4CAF50),
      ),
      drawer: Drawer(
        child: ListView(
          children: [
            UserAccountsDrawerHeader(
              decoration: BoxDecoration(
                color: Color(0xFF4CAF50),
              ),
              accountName: Row(
                children: [
                  CircleAvatar(
                    backgroundColor: Colors.white,
                    child: Text(
                      'HC',
                      style: TextStyle(fontSize: 20.0),
                    ),
                  ),
                  SizedBox(width: 16.0),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Restaurant',
                        style: TextStyle(
                          fontSize: 18.0,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      SizedBox(height: 2.0),
                      Text(
                        'headchef@gmail.com',
                        style: TextStyle(
                          fontSize: 14.0,
                          color: Colors.grey[300],
                        ),
                      ),
                    ],
                  ),
                ],
              ),
              accountEmail: null,
            ),
            ListTile(
              leading: Icon(Icons.home, color: Color(0xFF4CAF50)),
              title: Text('Home'),
              onTap: () {
                // Navigate to home page
              },
            ),
            ListTile(
              leading: Icon(Icons.list, color: Color(0xFF4CAF50)),
              title: Text('Orders'),
              onTap: () {
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => ListPage()),
                );
              },
            ),
            ListTile(
              leading: Icon(Icons.restaurant, color: Color(0xFF4CAF50)),
              title: Text('Restaurants'),
              onTap: () {
                // Navigate to restaurants page
              },
            ),
            ListTile(
              leading: Icon(Icons.settings, color: Color(0xFF4CAF50)),
              title: Text('Settings'),
              onTap: () {
                // Navigate to settings page
              },
            ),
            ListTile(
              leading: Icon(Icons.person, color: Color(0xFF4CAF50)),
              title: Text('About Me'),
              onTap: () {
                // Navigate to about page
              },
            ),
          ],
        ),
      ),
      body: Container(
        color: Colors.grey[200],
      ),
    );
  }
}