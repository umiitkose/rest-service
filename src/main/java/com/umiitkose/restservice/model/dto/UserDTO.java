package com.umiitkose.restservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "lütfen boş bırakmayınız")
    @Size(min = 2, message = "en az 2 karakter girmelisiniz.")
    private String name;
    @NotBlank(message = "lütfen boş bırakmayınız")
    @Size(min = 2, message = "en az 2 karakter girmelisiniz.")
    private String surname;
    @NotBlank(message = "lütfen boş bırakmayınız")
    @Email(message = "Lütfen email bilgisi giriniz.")
    private String email;
}
