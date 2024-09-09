import 'package:flutter/material.dart';
import 'add_order_page.dart';

class OrderListPage extends StatelessWidget {
  final List<Map<String, dynamic>> orders = [
    {'name': 'Pizza', 'note': 'Thin pizza', 'quantity': 1},
    {'name': 'Pizza', 'note': 'Thin pizza', 'quantity': 1},
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Order List'),
      ),
      body: ListView.builder(
        itemCount: orders.length,
        itemBuilder: (context, index) {
          final order = orders[index];
          return ListTile(
            title: Text(order['name']),
            subtitle: Text(order['note']),
            trailing: Text(order['quantity'].toString()),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: () {
          Navigator.push(
              context, MaterialPageRoute(builder: (context) => AddOrderPage()));
        },
      ),
    );
  }
}
