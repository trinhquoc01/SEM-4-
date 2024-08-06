import 'dart:io';
import 'package:mysql1/mysql1.dart';
import 'package:collection/collection.dart';

class Student {
  int id;
  String name;
  String phone;

  Student(this.id, this.name, this.phone);

  @override
  String toString() {
    return 'ID: $id, Name: $name, Phone: $phone';
  }
}

void main() async {
  final settings = ConnectionSettings(
      host: 'localhost',
      port: 3308,
      user: 'root',
      // password: '',
      db: 'school'
  );
  final conn = await MySqlConnection.connect(settings);

  List<Student> students = [];

  while (true) {
    print('''
      Menu:
      1. Thêm sinh viên
      2. Sửa sinh viên
      3. Xoá sinh viên
      4. Hiển thị danh sách sinh viên
      5. Thoát
      Chọn một thao tác: 
    ''');
    String? choice = stdin.readLineSync();

    switch (choice) {
      case '1':
        await addStudent(conn, students);
        break;
      case '2':
        await editStudent(conn);
        break;
      case '3':
        await deleteStudent(conn);
        break;
      case '4':
        await displayStudents(conn, students);
        break;
      case '5':
        await conn.close();
        print('Thoát chương trình');
        exit(0);
      default:
        print('Bạn chọn sai. Vui lòng chọn lại');
    }
  }
}

Future<void> addStudent(MySqlConnection conn, List<Student> students) async {
  print('Nhập tên sinh viên:');
  String? name = stdin.readLineSync();
  if (name == null || name.isEmpty) {
    print('Tên không đúng định dạng');
    return;
  }
  print('Nhập số điện thoại sinh viên:');
  String? phone = stdin.readLineSync();
  if (phone == null || phone.isEmpty) {
    print('Điện thoại không hợp lệ');
    return;
  }
  var result = await conn.query('insert into student (name, phone) values (?, ?)', [name, phone]);
  var id = result.insertId;
  if (id != null) {
    students.add(Student(id, name, phone));
    print('Sinh viên đã được thêm');
  } else {
    print('Thêm sinh viên lỗi!!!');
  }
}

Future<void> displayStudents(MySqlConnection conn, List<Student> students) async {
  var results = await conn.query("select id, name, phone from student");
  students.clear();
  for (var row in results) {
    students.add(Student(row['id'], row['name'], row['phone']));
  }
  if (students.isEmpty) {
    print('Danh sách sinh viên trống');
  } else {
    print('Danh sách sinh viên là:');
    for (var student in students) {
      print(student);
    }
  }
}

Future<void> editStudent(MySqlConnection conn) async {
  print('Nhập ID của sinh viên cần sửa:');
  String? id = stdin.readLineSync();
  if (id == null || id.isEmpty) {
    print('ID không hợp lệ.');
    return;
  }

  print('Nhập tên mới cho sinh viên:');
  String? name = stdin.readLineSync();
  if (name == null || name.isEmpty) {
    print('Tên không hợp lệ.');
    return;
  }

  print('Nhập số điện thoại mới:');
  String? phone = stdin.readLineSync();
  if (phone == null || phone.isEmpty) {
    print('Số điện thoại không hợp lệ.');
    return;
  }

  var result = await conn.query(
      'UPDATE student SET name = ?, phone = ? WHERE id = ?',
      [name, phone, int.tryParse(id)]
  );
  if (result.affectedRows == 1) {
    print('Thông tin sinh viên đã được cập nhật.');
  } else {
    print('Cập nhật không thành công.');
  }
}

Future<void> deleteStudent(MySqlConnection conn) async {
  print('Nhập ID của sinh viên cần xoá:');
  String? id = stdin.readLineSync();
  if (id == null || id.isEmpty) {
    print('ID không hợp lệ.');
    return;
  }

  var result = await conn.query(
      'DELETE FROM student WHERE id = ?',
      [int.tryParse(id)]
  );
  if (result.affectedRows == 1) {
    print('Sinh viên đã được xoá.');
  } else {
    print('Không tìm thấy sinh viên để xoá.');
  }
}
