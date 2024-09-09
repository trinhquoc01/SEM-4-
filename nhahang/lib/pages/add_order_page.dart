import 'package:flutter/material.dart';

class AddOrderPage extends StatelessWidget {
  final TextEditingController nameController = TextEditingController();
  final TextEditingController noteController = TextEditingController();
  final TextEditingController quantityController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Add new an order'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: <Widget>[
            TextField(
              controller: nameController,
              decoration: InputDecoration(labelText: 'Dish name'),
            ),
            TextField(
              controller: noteController,
              decoration: InputDecoration(labelText: 'Notes'),
            ),
            TextField(
              controller: quantityController,
              decoration: InputDecoration(labelText: 'Quantity'),
              keyboardType: TextInputType.number,
            ),
            SizedBox(height: 20),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: <Widget>[
                ElevatedButton(
                  onPressed: () {
                    // Logic thêm đơn hàng vào database Firebase sẽ ở đây
                  },
                  child: Text('Add Item'),
                ),
                ElevatedButton(
                  onPressed: () {
                    nameController.clear();
                    noteController.clear();
                    quantityController.clear();
                  },
                  child: Text('Reset'),
                ),
              ],
            )
          ],
        ),
      ),
    );
  }
}
