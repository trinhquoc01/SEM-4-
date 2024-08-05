import 'dart:io';

import 'package:mysql1/mysql1.dart';
import 'dart:collection';

class Student {
  int id;
  String name;
  String phone;

  Student({required this.id, required this.name, required this.phone});

  @override
  bool operator ==(Object other) =>
      identical(this, other) ||
          other is Student &&
              runtimeType == other.runtimeType &&
              phone == other.phone;

  @override
  int get hashCode => phone.hashCode;
}

class DatabaseHelper {
  final ConnectionSettings settings = ConnectionSettings(
    host: 'localhost',
    port: 3308,
    user: 'root',
    // password: ''
    db: 'school',
    timeout: Duration(seconds: 30),
  );

  Future<MySqlConnection> getConnection() async {
    try {
      return await MySqlConnection.connect(settings);
    } catch (e) {
      print('Không thể kết nối tới Database');
      rethrow;
    }
  }

  Future<void> createStudent(Student student) async {
    MySqlConnection? conn;
    conn = await getConnection();

    // Check if the phone number already exists
    var results = await conn.query('SELECT * FROM Student WHERE phone = ?', [student.phone]);
    if (results.isEmpty) {
      await conn.query('INSERT INTO Student(name,phone) VALUES(?,?)', [student.name, student.phone]);
      print('Đã thêm sinh viên mới');
    } else {
      print('Số điện thoại đã tồn tại');
    }

    await conn.close();
  }

  Future<HashSet<Student>> getStudents() async {
    MySqlConnection? conn;
    conn = await getConnection();
    var results = await conn.query("SELECT * FROM Student");
    HashSet<Student> students = HashSet<Student>();
    for (var row in results) {
      students.add(Student(id: row[0], name: row[1], phone: row[2]));
    }
    await conn.close();
    return students;
  }

  Future<void> deleteStudent(int id) async {
    MySqlConnection? conn;
    conn = await getConnection();
    await conn.query('DELETE FROM Student WHERE id = ?', [id]);
    await conn.close();
  }

  Future<void> updateStudent(Student student) async {
    MySqlConnection? conn;
    conn = await getConnection();

    // Check if the new phone number already exists
    var results = await conn.query('SELECT * FROM Student WHERE phone = ? AND id != ?', [student.phone, student.id]);
    if (results.isEmpty) {
      await conn.query('UPDATE Student SET name = ?, phone = ? WHERE id = ?', [student.name, student.phone, student.id]);
      print('Đã cập nhật thông tin sinh viên');
    } else {
      print('Số điện thoại đã tồn tại');
    }

    await conn.close();
  }
}

void main() async {
  DatabaseHelper dbHelper = DatabaseHelper();

  while (true) {
    print('1. Thêm sinh viên mới');
    print('2. Hiển thị tất cả sinh viên');
    print('3. Cập nhật thông tin sinh viên');
    print('4. Xóa sinh viên');
    print('5. Thoát');

    print('Chọn một tùy chọn:');

    String? choice = stdin.readLineSync();

    switch (choice) {
      case '1':
        print('Nhập tên sinh viên:');
        String? name = stdin.readLineSync();
        print('Nhập số điện thoại:');
        String? phone = stdin.readLineSync();
        if (name != null && phone != null) {
          Student newStudent = Student(id: 0, name: name, phone: phone);
          await dbHelper.createStudent(newStudent);
        }
        break;
      case '2':
        HashSet<Student> students = await dbHelper.getStudents();
        for (var student in students) {
          print('${student.id} - ${student.name} - ${student.phone}');
        }
        break;
      case '3':
        HashSet<Student> students = await dbHelper.getStudents();
        for (var student in students) {
          print('${student.id} - ${student.name} - ${student.phone}');
        }
        print('Nhập ID sinh viên cần cập nhật:');
        String? idStr = stdin.readLineSync();
        if (idStr != null) {
          int id = int.parse(idStr);
          try {
            Student studentToUpdate = students.firstWhere((student) => student.id == id);
            print('Nhập tên mới:');
            String? newName = stdin.readLineSync();
            print('Nhập số điện thoại mới:');
            String? newPhone = stdin.readLineSync();
            if (newName != null && newPhone != null) {
              studentToUpdate.name = newName;
              studentToUpdate.phone = newPhone;
              await dbHelper.updateStudent(studentToUpdate);
            }
          } catch (e) {
            print('Không tìm thấy sinh viên với ID đã cho');
          }
        }
        break;
      case '4':
        HashSet<Student> students = await dbHelper.getStudents();
        for (var student in students) {
          print('${student.id} - ${student.name} - ${student.phone}');
        }
        print('Nhập ID sinh viên cần xóa:');
        String? idStr = stdin.readLineSync();
        if (idStr != null) {
          int id = int.parse(idStr);
          await dbHelper.deleteStudent(id);
          print('Đã xóa sinh viên');
        }
        break;
      case '5':
        exit(0);
      default:
        print('Lựa chọn không hợp lệ, vui lòng chọn lại');
        break;
    }
  }
}
