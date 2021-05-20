package com.onemount.cinema.service;

import com.github.javafaker.Faker;
import com.onemount.cinema.enums.EventStatus;
import com.onemount.cinema.enums.FilmStatus;
import com.onemount.cinema.model.*;
import com.onemount.cinema.repository.CinemaRepository;
import com.onemount.cinema.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateDataService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    private final Faker faker = new Faker();


    @Transactional
    public void generateSampleData() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        Film film1 = new Film();
        Film film2 = new Film();
        Actor actor1 = new Actor("Park Seo Joon", 31, "Korea");
        Actor actor2 = new Actor("Ahn Sung Ki", 32, "Korea");
        Actor actor3 = new Actor("Woo Do Hwan", 31, "Korea");
        Actor actor4 = new Actor("Choi Woo Sik", "Korea");
        Genre adventure = new Genre("Adventure","");
        Genre comedy = new Genre("Comedy","");
        Genre cartoon = new Genre("Cartoon","");
        Genre fiction = new Genre("Fiction","");
        Genre Action = new Genre("Action","");
        List<Actor> actors1 = new ArrayList<>();
        actors1.add(actor1);
        actors1.add(actor2);
        actors1.add(actor3);
        actors1.add(actor4);
        film1.setActors(actors1);
        film1.addGenre(Action);
        film1.setDescription("Sau khi bản thân bỗng nhiên sở hữu “Bàn tay diệt quỷ”, võ sĩ MMA Yong Hoo (Park Seo Joon thủ vai) đã dấn thân vào hành trình trừ tà, trục quỷ đối đầu với Giám Mục Bóng Tối (Woo Do Hwan) – tên quỷ Satan đột lốt người. Từ đó sự thật về cái chết của cha Yong Hoo cũng dần được hé lộ cũng như nguyên nhân anh trở thành “người được chọn”.");
        film1.setRunningTime(120);
        film1.setTitle("BÀN TAY DIỆT QUỶ");
        film1.setReleaseDate(formatter.parse("09/04/2021"));
        film1.setStatus(FilmStatus.ON_THEATER);

        filmRepository.save(film1);

        Actor actor5 = new Actor("Sung Dong Il", "Korea");
        Actor actor6 = new Actor("Ha Ji Won,", "Korea");
        Actor actor7 = new Actor("Kim Hiewon", "Korea");
        Actor actor8 = new Actor("Park Soi", "Korea");

        List<Actor> actors2 = new ArrayList<>();
        actors2.add(actor5);
        actors2.add(actor6);
        actors2.add(actor7);
        actors2.add(actor8);
        film2.setActors(actors2);
        film2.addGenre(comedy);
        film2.setDescription("Du-seok (Sung Dong Il) và Jong-bae (Kim Hiewon) là hai gã chuyên đòi nợ thuê có máu mặt. Để uy hiếp một con nợ, cả hai đã bắt Seung-yi (Park Soi) - một bé gái 9 tuổi làm vật thế chấp cho số nợ của mẹ cô bé. Tuy nhiên, mẹ của Seung-yi lại bị trục xuất về nước, và hai ông chú đành nhận trách nhiệm trông chừng Seung-yi đến khi cô bé được một gia đình giàu có nhận nuôi. Khi phát hiện ra Seung-yi nhỏ bé bị bán đi làm công cho một bà chủ vô trách nhiệm, Du-seok đã tìm đến để chuộc lại cô bé. Mặc dù Seung-yi vốn là Du-seok và Jong-bae không hề mong muốn, cô bé dần trở thành cục cưng yêu quý và cả 3 sống bên nhau như một gia đình. (CHIẾU LẠI từ 5/5/21)");
        film2.setRunningTime(150);
        film2.setTitle("CỤC NỢ HÓA CỤC CƯNG");
        film2.setReleaseDate(formatter.parse("09/10/2021"));
        film2.setStatus(FilmStatus.INCOMING);

        filmRepository.save(film2);

        Cinema cinema1 = new Cinema("CGV Vincom Mega Mall", "458 Minh Khai, Ha Noi");
        Cinema cinema2 = new Cinema("CGV Trương Định Plaza", "416 Trương Định, Ha Noi");
        generateCinemaAndEvent(cinema1, film1);
        generateCinemaAndEvent(cinema2, film2);

    }

    @Transactional
    public void generateCinemaAndEvent(Cinema cinema, Film film) throws ParseException {
        List<Staff> staffList = new ArrayList<>();
        List<Room> roomList = new ArrayList<>();
        for(int i=0; i<30;i++){
            Staff staff = new Staff(faker.name().fullName(), faker.code().asin(),faker.number().numberBetween(18,30));
            staffList.add(staff);
        }

        Room room1 = new Room("A");
        room1 = generateSeatAndEvent(room1, film);
        Room room2 = new Room("B");
        room2 = generateSeatAndEvent(room2, film);

        roomList.add(room1);
        roomList.add(room2);

        cinema.setStaffs(staffList);
        cinema.setRooms(roomList);

        cinemaRepository.save(cinema);

    }

    @Transactional
    public Room generateSeatAndEvent(Room room, Film film) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        List<Seat> roomSeat = new ArrayList<>();
        List<Event> events = new ArrayList<>();
        String[] rowList = {"A", "B", "C"};
        for (String row: rowList) {
            for (int i=0;i<5;i++){
                Seat seat = new Seat(row,i);

                Event event = new Event();
                event.setStartTime(formatter.parse("20/05/2021 19:00:00"));
                event.setEndTime(formatter.parse("20/05/2021 21:00:00"));
                event.setStatus(EventStatus.AVAILABLE);
                event.setPrice(faker.number().numberBetween(50000, 200000));
                event.setSeat(seat);

                events.add(event);
                roomSeat.add(seat);
            }
        }
        film.setEvents(events);
        filmRepository.save(film);
        room.setSeats(roomSeat);
        return room;
    }
}
