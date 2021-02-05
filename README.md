# Plantor - მოუარე მცენარეებს მშვიდად

### About this app

Plantor წარმოადგენს უფასო პროგრამას 
რომელიც შეგახსენებთ თუ როდის არის
თქვენი მცენარის მორწყვის დრო. თუ 
გიყვართ მცენარეები, მაგრამ გადატვირთული
გრაფიკის გამო გავიწყდებათ ხოლმე მათი
მორწყვა, ეს პროგრამა სწორედ თქვენთვის 
არის. გადაარჩინე მეტი სიცოცხლე.


### Features: 

- შექმენი სხვადასხვა ექაუნთები, მაგალითად შენი სახლისთვის და ოფისისთვის
- ატვირთე შენი მცენარის ფოტოსურათი
- მიიღე შეტყობინება როდესაც მცენარის წყალი ჭირდება
- თავად განსაზღვრე თითოეული მცენარის მორწყვის დრო
- ნახე თუ ბოლოს როდის მორწყე შენი მცენარე 
- მარტივი და მოქნილი დიზაინი 

### How to use: 

- ატვირთე მცენარის ფოტოსურათი და მიუთითე თუ 
კვირის რომელ დღეს და კონკრეტულ რომელ საათზე უნდა მორწყა შენი მცენარე
- მცენარის მორწყვის შემდეგ დააკლიკე შესაბამის მცენარეს 
- დაამატე მცენარეები და ისიამოვნე მათი მოვლით 

<br>

ჩვენი პროგრამა Plantor რეგისტაცია/აუთენთიფიკაციისთვის იყენებს Firebase Authentication სისტემას. 
პირველი გვერდზე მოცემულია ორი თავისუფალი ველი სადაც უნდა შევიყვანოთ ჩვენი ექაუნთის მეილი და პაროლი 
მსგავსი მეილის არარსებობის შემთხვევაში პროგრამა გადაგვიყვანს რეგისტრაციის გვერდზე, იმ შემთხვევაში თუ პაროლი არ გვახსოვს შეგვიძლია გამოვიყენოთ Recover Password ფუნქცია. 

აუთენთიფიკაციის გავლის შემდეგ Add Plant ნავიგაციის ველზე გადასვლისას შეგვიძლია ავტვირთოთ ჩვენი მცენარის ფოტოსურათი აპარატის შიდა მეხსიებიდან, რომელიც ინახე Firebase Storage ში
შევიყვანოთ მცენარის სახელწოდება, მორწყვის საათი და ავირჩიოთ თუ რა დღეებში გვინდა მოგვივიდეს შეტყობინება ჩვენი მცენარის შესახებ, ამ ინფორმაციის შესანახად კი ვიყენებთ Firebase Realtime Database 
და სწორედ ამ ბაზის დახმარებით ხდება ინფორმაციის ჩატვირთვა ჩვენს პროგრამაში, ორი ჩაშენებული ფუნქციის Worker და NotificationBuilder და Realtime Database ინფრომაციის სინქნორიზაციის შედეგად კი ვიღებთ ზუსტ შეტყობინებებს დროის ზუსტ მონაკვეთში
ასე რომ თქვენი ინფორმაცია სანდო ხელშია და თქვენი მოწყობილობის დაღუპვა არ გამოიწვევს თქვენი მცენარის დაღუპვას რადგან ნებისმიერ სხვა მოწყობილობაზე შეგიძლიათ დააინსტალიროთ ჩვენი პროგრამა 
გაიაროთ აუთენთიფიკაცია და დაიბრუნოთ წვდომა ინფორმაციაზე თქვენი მცენარეების შესახებ. 

### Future Improvements:

1. მცენარის წაშლის შემდეგ, მცენარის სურათი cloud storage-დან არ იშლება (თუმცა თვითონ აპლიკაციას პრობლემას არ უქმნის)
