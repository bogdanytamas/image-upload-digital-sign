package hu.ponte.hr.controller;

import hu.ponte.hr.entity.ImageMetaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author zoltan
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageMeta
{
	private String id;
	private String name;
	private String mimeType;
	private long size;
	private String digitalSign;

	public ImageMeta(ImageMetaEntity imageMetaEntity) {
		this.id = imageMetaEntity.getId().toString();
		this.name = imageMetaEntity.getName();
		this.mimeType = imageMetaEntity.getMimeType();
		this.size = imageMetaEntity.getSize();
		this.digitalSign = imageMetaEntity.getDigitalSign();
	}
}
