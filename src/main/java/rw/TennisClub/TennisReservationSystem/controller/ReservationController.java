package rw.TennisClub.TennisReservationSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rw.TennisClub.TennisReservationSystem.model.Reservation;
import rw.TennisClub.TennisReservationSystem.service.ReservationService;

import java.io.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/add")
    public String showAddReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation()); // Create a new Reservation object
        return "add-reservation"; // Return the add reservation template
    }





    @PostMapping
    public String addReservation(@ModelAttribute Reservation reservation) {
        reservationService.saveReservation(reservation); // Save the reservation using your service
        return "redirect:/admin/reservations"; // Redirect to the reservations list after saving
    }

    @PostMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Integer id) {
        reservationService.deleteReservation(id); // Call the service to delete the reservation
        return "redirect:/admin/reservations"; // Redirect to the reservations list after deletion
    }

    @GetMapping("/edit/{id}")
    public String showEditReservationForm(@PathVariable Integer id, Model model) {
        Reservation reservation = reservationService.getReservationById(id); // Fetch the reservation by ID
        model.addAttribute("reservation", reservation); // Add the reservation to the model
        return "edit-reservation"; // Return the edit reservation template
    }

    @PostMapping("/update")
    public String updateReservation(@ModelAttribute Reservation reservation) {
        reservationService.updateReservation(reservation); // Call the service to update the reservation
        return "redirect:/admin/reservations"; // Redirect to the reservations list after updating
    }

    @GetMapping("/search")
    public String showSearchForm() {
        return "search-reservation"; // Return the search reservation template
    }

    @GetMapping("/search/results")
    public String searchReservations(@RequestParam(required = false) String courtnumber,
                                     @RequestParam(required = false) String date,
                                     Model model) {
        List<Reservation> reservations = reservationService.searchReservations(courtnumber, date); // Call the service to search for reservations
        model.addAttribute("reservations", reservations); // Add the list of reservations to the model
        return "reservation-list"; // Return the template that displays the list of reservations
    }

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<ByteArrayResource> downloadReservations() throws IOException {
        List<Reservation> reservations = reservationService.getAllReservations(); // Fetch all reservations from the service

        // Create CSV content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);

        // Write CSV header
        writer.println("ID,Court Number,Date,Time"); // Adjust according to your Reservation fields

        // Write reservation data
        for (Reservation reservation : reservations) {
            writer.printf("%d,%s,%s,%s%n",
                    reservation.getReservation_id(),
                    reservation.getCourtnumber(),
                    reservation.getDate(),
                    reservation.getTime()); // Adjust according to your Reservation fields
        }
        writer.flush();
        writer.close();

        ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

        // Set the content type and attachment header
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reservations.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }

    @GetMapping("/upload")
    public String showUploadPage() {
        return "upload"; // Return the combined upload page template
    }

    @PostMapping("/upload")
    public String uploadReservations(@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "upload";
        }

        try {
            List<Reservation> reservationList = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            reader.readLine(); // Skip header line

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Reservation reservation = new Reservation();
                reservation.setCourtnumber(data[0]);
                reservation.setDate(Date.valueOf(data[1]));
                reservation.setTime(Time.valueOf(data[2]));
                reservationList.add(reservation);
            }

            reservationService.saveAll(reservationList);
            model.addAttribute("message", "Reservations file uploaded successfully!");
            return "redirect:/admin/reservations";
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload reservations file: " + e.getMessage());
        }

        return "upload";
    }
}