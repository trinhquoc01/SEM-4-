class Product {
  final String id;
  final String name;
  final String description;
  final String price;
  final String saleDate;
  final String image;

  Product({
    required this.id,
    required this.name,
    required this.description,
    required this.price,
    required this.saleDate,
    required this.image,
  });

  factory Product.fromJson(Map<String, dynamic> json) {
    return Product(
      id: json['id'],
      name: json['name'],
      description: json['description'],
      price: json['price'],
      saleDate: json['sale_date'],
      image: json['image'],
    );
  }
}
