import 'dart:convert';
import 'dart:io';

class Student {
  int id;
  String name;
  List<Subject> subjects;

  Student({required this.id, required this.name, required this.subjects});

  factory Student.fromJson(Map<String, dynamic> json) {
    var subjectsJson = json['subjects'] as List;
    List<Subject> subjectsList =
    subjectsJson.map((i) => Subject.fromJson(i)).toList();

    return Student(
        id: json['id'], name: json['name'], subjects: subjectsList);
  }

  Map<String, dynamic> toJson() => {
    'id': id,
    'name': name,
    'subjects': subjects.map((subject) => subject.toJson()).toList(),
  };
}

class Subject {
  String name;
  List<int> scores;

  Subject({required this.name, required this.scores});

  factory Subject.fromJson(Map<String, dynamic> json) {
    var scoresJson = json['scores'].cast<int>();
    return Subject(name: json['name'], scores: scoresJson);
  }

  Map<String, dynamic> toJson() => {
    'name': name,
    'scores': scores,
  };
}

void main() async {
  // Đọc dữ liệu từ file JSON
  final file = File('student.json');
  final jsonString = await file.readAsString();
  final jsonData = json.decode(jsonString);

  List<Student> students = (jsonData['students'] as List)
      .map((i) => Student.fromJson(i))
      .toList();

  // Hiển thị toàn bộ sinh viên
  print('1. Hiển thị toàn bộ sinh viên:');
  for (var student in students) {
    print('ID: ${student.id}, Name: ${student.name}');
    for (var subject in student.subjects) {
      print('  Subject: ${subject.name}, Scores: ${subject.scores}');
    }
  }

  // Thêm sinh viên mới
  print('\n2. Thêm sinh viên mới:');
  var newStudent = Student(
      id: 3,
      name: 'Nguyen Van A',
      subjects: [Subject(name: 'Math', scores: [9, 8, 7])]);
  students.add(newStudent);

  // Sửa thông tin sinh viên
  print('\n3. Sửa thông tin sinh viên:');
  var studentToEdit = students.firstWhere((student) => student.id == 1);
  studentToEdit.name = 'Truong Gia Binh Updated';

  // Tìm kiếm sinh viên theo Tên hoặc ID
  print('\n4. Tìm kiếm sinh viên theo Tên hoặc ID:');
  var searchId = 2;
  var foundStudent =
  students.firstWhere((student) => student.id == searchId, orElse: () => Student(id: 0, name: 'Not Found', subjects: []));
  print('Found Student: ID: ${foundStudent.id}, Name: ${foundStudent.name}');

  // Hiển thị các sinh viên có điểm môn thi cao nhất
  print('\n5. Hiển thị các sinh viên có điểm môn thi cao nhất:');
  for (var student in students) {
    for (var subject in student.subjects) {
      int maxScore = subject.scores.reduce((curr, next) => curr > next ? curr : next);
      print('Student: ${student.name}, Subject: ${subject.name}, Highest Score: $maxScore');
    }
  }

  // Cập nhật lại dữ liệu vào file JSON
  var updatedJsonData = {'students': students.map((student) => student.toJson()).toList()};
  await file.writeAsString(json.encode(updatedJsonData));
}
