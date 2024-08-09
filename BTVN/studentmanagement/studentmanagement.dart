import 'dart:convert';
import 'dart:io';

// Định nghĩa lớp Subject
class Subject {
  String name;
  List<int> scores;

  Subject({required this.name, required this.scores});

  factory Subject.fromJson(Map<String, dynamic> json) {
    return Subject(
      name: json['name'],
      scores: List<int>.from(json['scores']),
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'name': name,
      'scores': scores,
    };
  }
}

// Định nghĩa lớp Student
class Student {
  int id;
  String name;
  List<Subject> subjects;

  Student({required this.id, required this.name, required this.subjects});

  factory Student.fromJson(Map<String, dynamic> json) {
    var list = json['subjects'] as List;
    List<Subject> subjectList = list.map((i) => Subject.fromJson(i)).toList();

    return Student(
      id: json['id'],
      name: json['name'],
      subjects: subjectList,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'subjects': subjects.map((e) => e.toJson()).toList(),
    };
  }
}

// Hàm load dữ liệu từ file JSON
List<Student> loadStudents(String fileName) {
  final file = File(fileName);
  final jsonData = jsonDecode(file.readAsStringSync())['students'] as List;
  return jsonData.map((e) => Student.fromJson(e)).toList();
}

// Hàm lưu dữ liệu vào file JSON
void saveStudents(List<Student> students, String fileName) {
  final file = File(fileName);
  final jsonString =
  jsonEncode({'students': students.map((e) => e.toJson()).toList()});
  file.writeAsStringSync(jsonString);
}

// Hiển thị toàn bộ sinh viên
void displayAllStudents(List<Student> students) {
  for (var student in students) {
    print('ID: ${student.id}, Name: ${student.name}');
    for (var subject in student.subjects) {
      print('  Subject: ${subject.name}, Scores: ${subject.scores}');
    }
  }
}

// Thêm sinh viên mới
void addStudent(List<Student> students) {
  stdout.write('Enter student ID: ');
  int id = int.parse(stdin.readLineSync()!);

  stdout.write('Enter student name: ');
  String name = stdin.readLineSync()!;

  List<Subject> subjects = [];
  stdout.write('How many subjects? ');
  int subjectCount = int.parse(stdin.readLineSync()!);

  for (int i = 0; i < subjectCount; i++) {
    stdout.write('Enter subject name: ');
    String subjectName = stdin.readLineSync()!;

    List<int> scores = [];
    stdout.write('How many scores? ');
    int scoreCount = int.parse(stdin.readLineSync()!);

    for (int j = 0; j < scoreCount; j++) {
      stdout.write('Enter score ${j + 1}: ');
      int score = int.parse(stdin.readLineSync()!);
      scores.add(score);
    }

    subjects.add(Subject(name: subjectName, scores: scores));
  }

  students.add(Student(id: id, name: name, subjects: subjects));
}

// Chỉnh sửa thông tin sinh viên
void editStudent(List<Student> students) {
  stdout.write('Enter student ID to edit: ');
  int id = int.parse(stdin.readLineSync()!);

  try {
    var student = students.firstWhere(
            (student) => student.id == id,
        orElse: () => throw 'Student not found');

    stdout.write('Enter new name (leave blank to keep current): ');
    String newName = stdin.readLineSync()!;
    if (newName.isNotEmpty) {
      student.name = newName;
    }

    stdout.write('Do you want to edit subjects? (yes/no): ');
    String editSubjects = stdin.readLineSync()!;
    if (editSubjects.toLowerCase() == 'yes') {
      List<Subject> newSubjects = [];
      stdout.write('How many subjects? ');
      int subjectCount = int.parse(stdin.readLineSync()!);

      for (int i = 0; i < subjectCount; i++) {
        stdout.write('Enter subject name: ');
        String subjectName = stdin.readLineSync()!;

        List<int> scores = [];
        stdout.write('How many scores? ');
        int scoreCount = int.parse(stdin.readLineSync()!);

        for (int j = 0; j < scoreCount; j++) {
          stdout.write('Enter score ${j + 1}: ');
          int score = int.parse(stdin.readLineSync()!);
          scores.add(score);
        }

        newSubjects.add(Subject(name: subjectName, scores: scores));
      }

      student.subjects = newSubjects;
    }
  } catch (e) {
    print(e);
  }
}

// Tìm kiếm sinh viên theo tên hoặc ID
void searchStudent(List<Student> students) {
  stdout.write('Search by name or ID? (name/id): ');
  String choice = stdin.readLineSync()!;

  try {
    Student? student;
    if (choice.toLowerCase() == 'name') {
      stdout.write('Enter student name: ');
      String name = stdin.readLineSync()!;
      student = students.firstWhere(
              (student) => student.name.toLowerCase() == name.toLowerCase(),
          orElse: () => throw 'Student not found');
    } else if (choice.toLowerCase() == 'id') {
      stdout.write('Enter student ID: ');
      int id = int.parse(stdin.readLineSync()!);
      student = students.firstWhere(
              (student) => student.id == id,
          orElse: () => throw 'Student not found');
    } else {
      throw 'Invalid choice';
    }

    print('ID: ${student!.id}, Name: ${student.name}');
    for (var subject in student.subjects) {
      print('  Subject: ${subject.name}, Scores: ${subject.scores}');
    }
  } catch (e) {
    print(e);
  }
}

// Hiển thị sinh viên có điểm cao nhất trong môn học
void displayTopScorers(List<Student> students) {
  stdout.write('Enter subject name: ');
  String subjectName = stdin.readLineSync()!;

  int highestScore = 0;
  for (var student in students) {
    for (var subject in student.subjects) {
      if (subject.name.toLowerCase() == subjectName.toLowerCase()) {
        highestScore = highestScore <
            subject.scores.reduce((a, b) => a > b ? a : b)
            ? subject.scores.reduce((a, b) => a > b ? a : b)
            : highestScore;
      }
    }
  }

  print('Students with the highest score of $highestScore in $subjectName:');
  for (var student in students) {
    for (var subject in student.subjects) {
      if (subject.name.toLowerCase() == subjectName.toLowerCase() &&
          subject.scores.contains(highestScore)) {
        print('${student.name}');
      }
    }
  }
}

// Hàm chính
void main() {
  String fileName = 'Student.json';
  List<Student> students = loadStudents(fileName);

  while (true) {
    print('\nStudent Management Menu');
    print('1. Display all students');
    print('2. Add a new student');
    print('3. Edit student information');
    print('4. Search student by name or ID');
    print('5. Display top scorers in a subject');
    print('6. Exit');
    stdout.write('Choose an option: ');

    String choice = stdin.readLineSync()!;
    switch (choice) {
      case '1':
        displayAllStudents(students);
        break;
      case '2':
        addStudent(students);
        saveStudents(students, fileName);
        break;
      case '3':
        editStudent(students);
        saveStudents(students, fileName);
        break;
      case '4':
        searchStudent(students);
        break;
      case '5':
        displayTopScorers(students);
        break;
      case '6':
        saveStudents(students, fileName);
        print('Exiting program...');
        return;
      default:
        print('Invalid option. Please try again.');
    }
  }
}
