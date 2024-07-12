package org.example.mapper;

import org.example.dto.request.CreateCakeRequest;
import org.example.dto.request.UpdateCakeRequest;
import org.example.entity.Cake;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CakeMapper {
    @Mapping(target = "cakeName", source = "cakeName")
    @Mapping(target = "cakeDesc", source = "cakeDesc")
    @Mapping(target = "cakeOriginalPrice", source = "cakeOriginalPrice")
    @Mapping(target = "cakeSalePrice", source = "cakeSalePrice")
    @Mapping(target = "cakeProfileImagePath", source = "cakeProfileImagePath")
    Cake mapToCake(CreateCakeRequest createCakeRequest);

    @Mapping(target = "cakeName", source = "cakeName")
    @Mapping(target = "cakeDesc", source = "cakeDesc")
    @Mapping(target = "cakeOriginalPrice", source = "cakeOriginalPrice")
    @Mapping(target = "cakeSalePrice", source = "cakeSalePrice")
    @Mapping(target = "cakeProfileImagePath", source = "cakeProfileImagePath")
    @Mapping(target = "cakeCode", ignore = true)
    void mapToCake(UpdateCakeRequest updateCakeRequest, @MappingTarget Cake oldCake);
}
