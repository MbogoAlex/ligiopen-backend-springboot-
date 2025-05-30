package com.jabulani.ligiopen.model.dto;

import com.jabulani.ligiopen.model.aws.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClubDetailsMin {
    private Integer clubId;
    private String clubName;
    private String leagueName;
    private FileDto clubLogo;
    private List<FileDto> clubPhotos;
}
